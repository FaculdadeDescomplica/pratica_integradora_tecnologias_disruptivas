import React, {useState, useEffect} from 'react';
import { FormControl, InputLabel, Input, FormHelperText } from '@mui/material';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';

import InstanciaAxios from '../../api/InstanciaAxios';

//Declaração do componente EditarTarefa, recebendo como props, do Componente ListarTarefa, os states handCloseEditar,
// idTarefaSelecionada, tarefa e setTarefas
const EditarTarefa = ({handleCloseEditar, idTarefaSelecionada, tarefa, getTarefas}) =>{
  const [idTarefa, setIdTarefa] = useState();
  const [tarefaTitulo, setTarefaTitulo] = useState('');
  const [tarefaDescricao, setTarefaDescricao] = useState('');
  const [tarefaInicio, setTarefaInicio] = useState('');
  const [tarefaFim, setTarefaFim] = useState('');
  const [recursoId, setRecursoId] = useState('');
  const [statusTarefaId, setStatusTarefaId] = useState('');
  const [projetoId, setProjetoId] = useState('');

  const [projetos, setProjetos] = useState([]);
  const [recursos, setRecursos] = useState([]);
  const [statusTarefas, setStatusTarefas] = useState([]);

  const token = JSON.parse(localStorage.getItem("authUser"));

  //Abaixo setamos os valores dos states (que popularão o formulário mais abaixo) com os valores do state Tarefa,
  //  recebido como props do componente ListarTarefa.
  useEffect(() => {
    //console.log('Tarefa selecionada: ' + JSON.stringify(tarefa));

    //Pega apenas a data, no formato yyyy-mm-dd
    let dataInicio = tarefa.tarefaInicio.substring(0, tarefa.tarefaInicio.length - 10);
    let dataFim    = tarefa.tarefaFim.substring(0, tarefa.tarefaFim.length - 10);

    setIdTarefa(tarefa.tarefaId);
    setTarefaTitulo(tarefa.tarefaTitulo);
    setTarefaDescricao(tarefa.tarefaDescricao);
    setTarefaInicio(dataInicio);
    setTarefaFim(dataFim);
    setRecursoId(tarefa.recursoDTO.recursoId);
    setStatusTarefaId(tarefa.statusTarefaDTO.statusTarefaId);
    setProjetoId(tarefa.projetoDTO.projetoId);
  },[]);

  //Carrega do backend os dados dos selects 
  useEffect(() => {
    getProjetos();
    getRecursos();
    getStatusTarefas();
  }, []);

  const getProjetos = async () => {
    InstanciaAxios.get(
          `/projeto/id-nome`,
          {headers: { Authorization: `Bearer ${token.jwt}`}}
        )
        .then(result => { 
          console.log('result data(projetos): ' + JSON.stringify(result.data));
          setProjetos(result.data);
        });
  }
  
  const getRecursos = async () => {
    InstanciaAxios.get(
          `/recurso/id-nome`,
          {headers: { Authorization: `Bearer ${token.jwt}`}}
        )
        .then(result => { 
          console.log('result data(recursos): ' + JSON.stringify(result.data));
          setRecursos(result.data);
        });
  }

  const getStatusTarefas = async () => {
    InstanciaAxios.get(
          `/statusTarefa/id-nome`,
          {headers: { Authorization: `Bearer ${token.jwt}`}}
        )
        .then(result => { 
          console.log('result data(status tarefas): ' + JSON.stringify(result.data));
          setStatusTarefas(result.data);
        });
  }

  const handleRecurso = (event) => {
    setRecursoId(event.target.value);
  };

  const handleStatus = (event) => {
    setStatusTarefaId(event.target.value);
  };

  const handleProjeto = (event) => {
    setProjetoId(event.target.value);
  };

  const handleEditar = () => {
    //Para inspecionarmos nosso código, uma boa estratégia é utilizarmos o console.log.
    //  Com o console.log, podemos visualizar o seu conteúdo na aba Console, no inspecionador de elementos, na janela do navegador
    console.log(`tarefaId: ${idTarefaSelecionada} \n tarefaId: ${idTarefa} \n 
      titulo: ${tarefaTitulo} \n descrição: ${tarefaDescricao} \n 
      inicio: ${tarefaInicio} \n fim: ${tarefaFim} \n recurso: ${recursoId} \n 
      status: ${statusTarefaId}  \n projeto: ${projetoId}`);

    //Pega a data em millisec  
    let dataInicio = new Date(tarefaInicio).getTime();
    let dataFim = new Date(tarefaFim).getTime();

    InstanciaAxios.put(
      `/tarefa/${idTarefa}`,
      { tarefaTitulo, tarefaDescricao, tarefaInicio:dataInicio , tarefaFim:dataFim, statusTarefaId, recursoId, projetoId},
      {headers: { Authorization: `Bearer ${token.jwt}` }}
    )
    .then(result => { 
      console.log('result data edit tarefa: ' + JSON.stringify(result.data));
      //Chama o metodo de ListaTarefa, recebido como props, que recarrega, a partir do backend,
      //  a lista de tarefas
      getTarefas();
    });

    handleCloseEditar();
  };

  return(
    <Grid container spacing={2}>
      <Card sx={style}>
        <CardHeader
          title="Tarefas"
          subheader="Edição de Tarefas"
        /> 
        <CardContent sx={{
          width: '95%',
          maxWidth: '100%',
        }}>
          <Grid item xs={12}>
            <FormControl fullWidth>
              <Input id="tarefa_titulo" aria-describedby="tarefa_titulo_helper_text" value={tarefaTitulo} onChange={e => { setTarefaTitulo(e.target.value) }} />
              <FormHelperText id="tarefa_titulo_helper_text">Título da Tarefa.</FormHelperText>
            </FormControl>
          </Grid>
          <Grid item xs={12}>  
            <FormControl fullWidth>
              <Input id="tarefa_descricao" aria-describedby="tarefa_descricao_helper_text" value={tarefaDescricao} onChange={e => { setTarefaDescricao(e.target.value) }} />
              <FormHelperText id="tarefa_descricao_helper_text">Descrição da Tarefa.</FormHelperText>
            </FormControl>
          </Grid>
          <Grid container spacing={2} mt={1}>
            <Grid item xs={3}>  
              <FormControl>
                <Input id="tarefa_inicio" type="date" aria-describedby="tarefa_inicio_helper_text" value={tarefaInicio} onChange={e => { setTarefaInicio(e.target.value) }}
                  sx={{
                    color:'rgba(0, 0, 0, 0.6)',
                    fontWeight: 400,
                    paddingLeft:'13px'
                  }} 
                />
                <FormHelperText id="tarefa_inicio_helper_text">Início da Tarefa.</FormHelperText>
              </FormControl>
            </Grid>  
            <Grid item xs={3}>  
              <FormControl>
                <Input id="tarefa_fim" type="date" aria-describedby="tarefa_fim_helper_text" value={tarefaFim} onChange={e => { setTarefaFim(e.target.value) }}
                  sx={{
                    color:'rgba(0, 0, 0, 0.6)',
                    fontWeight: 400,
                    paddingLeft:'13px'
                  }} 
                />
                <FormHelperText id="tarefa_fim_helper_text">Fim da Tarefa.</FormHelperText>
              </FormControl>
            </Grid>
            <Grid item xs={3}>  
              <FormControl fullWidth>
                <InputLabel htmlFor="tarefa_recurso">Recurso</InputLabel>
                <Select
                  id="tarefa_recurso"
                  value={recursoId}
                  defaultValue={recursoId}
                  label="Recurso"
                  onChange={handleRecurso}
                  size="small"
                  sx={{
                    color:'rgba(0, 0, 0, 0.6)',
                    fontWeight: 400,
                  }} 
                >
                  {recursos.map((row, indice) => (
                    <MenuItem key={indice} value={row.recursoId}>{row.recursoNome}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={3}>  
              <FormControl fullWidth>
                <InputLabel htmlFor="tarefa_recurso">Status</InputLabel>
                <Select
                  id="tarefa_status"
                  value={statusTarefaId}
                  label="Status"
                  onChange={handleStatus}
                  size="small"
                  sx={{
                    color:'rgba(0, 0, 0, 0.6)',
                    fontWeight: 400,
                  }} 
                >
                  {statusTarefas.map((row, indice) => (
                    <MenuItem key={indice} value={row.statusTarefaId}>{row.statusDescricao}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
          </Grid>
          <Grid container spacing={2} mt={1}>
            <Grid item xs={3}>  
              <FormControl fullWidth>
                <InputLabel htmlFor="tarefa_projeto">Projeto</InputLabel>
                <Select
                  id="tarefa_projeto"
                  value={projetoId}
                  label="Projeto"
                  onChange={handleProjeto}
                  size="small"
                  sx={{
                    color:'rgba(0, 0, 0, 0.6)',
                    fontWeight: 400,
                  }} 
                >
                  {projetos.map((row, indice) => (
                    <MenuItem key={indice} value={row.projetoId}>{row.projetoNome}</MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>  
          </Grid>
          <Grid container spacing={2} mt={1}>
            <Grid container spacing={2} pl={2} mt={2}>
              <Grid item xs={1}>
                <Button size="small" variant="contained" onClick={handleEditar}>Salvar</Button>
              </Grid>  
              <Grid item xs={1}>  
                <Button size="small" variant="outlined" onClick={handleCloseEditar}>Cancelar</Button>  
              </Grid>
            </Grid>  
          </Grid>
        </CardContent>
      </Card>
    </Grid>
  );
}

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '60%',
  bgcolor: 'background.paper',
  p: 4,
};

export default EditarTarefa;