import React, { useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Modal from '@mui/material/Modal';

import CriarTarefa from './CriarTarefa';
import EditarTarefa from './EditarTarefa';

import InstanciaAxios from '../../api/InstanciaAxios';

//A função abaixo é usada para criar o array contendo os dados iniciais da listagem de tarefas.
function createData(
  tarefaId: number,
  tituloTarefa: string,
  descricaoTarefa: string,
  inicioTarefa: string,
  fimTarefa: string,
  statusTarefa: string,
  recursoTarefa: string,
) {
  return { tarefaId, tituloTarefa, descricaoTarefa, inicioTarefa, fimTarefa, statusTarefa, recursoTarefa };
}

//Componente ListarTarefa
const ListarTarefa = () => {
  const [open, setOpen] = useState(false);
  const [openEditar, setOpenEditar] = useState(false);
  const [tarefas, setTarefas] = useState([]);
  const [tarefa, setTarefa] = useState();
  const [tarefaIdSelecionada, setTarefaIdSelecionada] = useState([]);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleOpenEditar = () => setOpenEditar(true);
  const handleCloseEditar = () => setOpenEditar(false);

  const token = JSON.parse(localStorage.getItem("authUser"));

  useEffect(() => {
    getTarefas();
  }, []);

  const getTarefas = async () => {
    InstanciaAxios.get(
          `/tarefa`,
          {headers: { Authorization: `Bearer ${token.jwt}` }}
        )
        .then(result => { 
          console.log('result data: ' + JSON.stringify(result.data));
          setTarefas(result.data);
        });
  }

  const handleEditar = (id) => {
    setTarefaIdSelecionada(id);

    console.log('id: ' + id);

    //Objeto local para armazenamento da tarefa filtrada de acordo com a seleção do usuário
    let tarefaParaEditar = tarefas.filter(obj => {
      return obj.tarefaId === id;
    })[0];

    console.log('tarefaParaEditar: ' + JSON.stringify(tarefaParaEditar));

    //Atribuição do Objeto local, setado acima, ao state Tarefa
    setTarefa(tarefaParaEditar);

    //Seta como true o state responsável pela exibição do Model de Editar Tarefa
    setOpenEditar(true)
  };

  const handleDeletar = (id) => {
    InstanciaAxios.delete(
      `/tarefa/${id}`,
      {headers: { Authorization: `Bearer ${token.jwt}` }}
    )
    .then(result => { 
      console.log('result data delete tarefa: ' + JSON.stringify(result.data));

      if(result.data){
        alert("Tarefa excluída com sucesso!");
      }else{
        alert("Não foi possível excluir a Tarefa.");
      }

      //Chama o metodo de ListaTarefa, recebido como props, que recarrega, a partir do backend,
      //  a lista de tarefas
      getTarefas();
    });
  };

    return(
    <>
    <Card>
        <CardHeader
          title="Tarefas"
          subheader="Listagem de Tarefas"
        /> 
        <CardContent>
            <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
                <TableHead>
                <TableRow>
                    <TableCell>#</TableCell>
                    <TableCell>Título</TableCell>
                    <TableCell align="right">Descrição</TableCell>
                    <TableCell align="right">Data de Início</TableCell>
                    <TableCell align="right">Data de Finalização</TableCell>
                    <TableCell align="right">Status</TableCell>
                    <TableCell align="right">Recurso</TableCell>
                    <TableCell align="left"></TableCell>
                    <TableCell align="left"></TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {tarefas.map((row, indice) => (
                    <TableRow
                    key={indice}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                    >
                      <TableCell component="th" scope="row">
                          {row.tarefaId}
                      </TableCell>
                      <TableCell component="th" scope="row">
                          {row.tituloTarefa}
                      </TableCell>
                      <TableCell align="right">{row.tarefaTitulo}</TableCell>
                      <TableCell align="right">{row.tarefaInicio}</TableCell>
                      <TableCell align="right">{row.tarefaFim}</TableCell>
                      <TableCell align="right">{row.statusTarefaDTO.statusDescricao}</TableCell>
                      <TableCell align="right">{row.recursoDTO.recursoNome}</TableCell>
                      <TableCell align="center">
                        <Button variant="contained" color="success" onClick={() => handleEditar(row.tarefaId)}><EditIcon fontSize="small" /></Button>            
                      </TableCell>
                      <TableCell align="center">
                        <Button variant="contained" color="error" onClick={() => handleDeletar(row.tarefaId)}><DeleteIcon fontSize="small" /></Button>            
                      </TableCell>
                    </TableRow>
                ))}
                </TableBody>
            </Table>
            </TableContainer>
        </CardContent>
        <CardActions>
            <Button size="small" variant="contained" onClick={handleOpen}>Criar Tarefa</Button>
            <Button size="small" variant="outlined">Cancelar</Button>
      </CardActions> 
    </Card>
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <div>
          <CriarTarefa handleClose={handleClose} tarefas={tarefas} setTarefas={setTarefas} getTarefas={getTarefas} />
        </div>
      </Modal>  
    </div>
    <div>
      <Modal
        open={openEditar}
        onClose={handleCloseEditar}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <div>
          <EditarTarefa handleCloseEditar={handleCloseEditar} tarefaIdSelecionada={tarefaIdSelecionada} tarefas={tarefas} tarefa={tarefa} getTarefas={getTarefas} />
        </div>
      </Modal>  
    </div>
  </>    
 );
};
 
export default ListarTarefa;