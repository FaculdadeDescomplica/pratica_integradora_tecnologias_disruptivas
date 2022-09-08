import React, {useState, useEffect} from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { Card } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';

import Button from 'react-bootstrap/Button';

//Declaração do componente EditarTarefa, recebendo como props, do Componente ListarTarefa, os states handCloseEditar,
// idTarefaSelecionada, tarefas, tarefa e setTarefas
const EditarTarefa = ({handleCloseEditar, idTarefaSelecionada, tarefas, tarefa, setTarefas}) =>{
  const [idTarefa, setIdTarefa] = useState();
  const [tituloTarefa, setTituloTarefa] = useState('');
  const [descricaoTarefa, setDescricaoTarefa] = useState('');
  const [inicioTarefa, setInicioTarefa] = useState('');
  const [fimTarefa, setFimTarefa] = useState('');
  const [recursoTarefa, setRecursoTarefa] = useState('');
  const [statusTarefa, setStatusTarefa] = useState('');

  //Abaixo setamos os valores dos states (que popularão o formulário mais abaixo) com os valores do state Tarefa,
  //  recebido como props do componente ListarTarefa.
  useEffect(() => {
    //console.log('Tarefa selecionada: ' + JSON.stringify(tarefa));
    setIdTarefa(idTarefaSelecionada);
    setTituloTarefa(tarefa.tituloTarefa);
    setDescricaoTarefa(tarefa.descricaoTarefa);
    setInicioTarefa(tarefa.inicioTarefa);
    setFimTarefa(tarefa.fimTarefa);
    setRecursoTarefa(tarefa.recursoTarefa);
    setStatusTarefa(tarefa.statusTarefa);
  },[]);

  const handleRecurso = (event) => {
    setRecursoTarefa(event.target.value);
  };

  const handleStatus = (event) => {
    setStatusTarefa(event.target.value);
  };

  const handleEditar = () => {
    //console.log(`id: ${idTarefa} \n titulo: ${tituloTarefa} \n descrição: ${descricaoTarefa} \n inicio: ${inicioTarefa} \n fim: ${fimTarefa} \n recurso: ${recursoTarefa} \n status: ${statusTarefa}`);
    //console.log('idTarefaSelecionada: ' + idTarefaSelecionada);
    setTarefas(current =>
      current.map(obj => {
        if (obj.idTarefa === idTarefaSelecionada) {
          console.log('obj: ' + JSON.stringify(obj));          
          return {...obj, 
              idTarefa:idTarefaSelecionada,
              tituloTarefa:tituloTarefa,
              descricaoTarefa:descricaoTarefa,
              inicioTarefa:inicioTarefa,
              fimTarefa:fimTarefa,
              recursoTarefa:recursoTarefa,
              statusTarefa:statusTarefa
          };
        }

        return obj;
      }),
    );

    //console.log(`Tarefas Editadas: ` + JSON.stringify(tarefas));
    handleCloseEditar();
  };

  return(
    <Container fluid>
      <Card sx={style}>
        <Card.Body> 
          <Card.Title>Edição de Tarefas</Card.Title>
          <Card.Text>
            <Form>
              <Row md={12}>
                <Form.Group className="mb-3" controlId="tituloTarefa">
                  <Form.Control type="text" value={tituloTarefa} onChange={e => { setTituloTarefa(e.target.value) }} />
                  <Form.Text className="text-muted">
                    Título da Tarefa.
                  </Form.Text>
                </Form.Group>
              </Row>
              <Row md={12}>  
                <Form.Group className="mb-3" controlId="descricaoTarefa">
                  <Form.Control type="text" value={descricaoTarefa} onChange={e => { setDescricaoTarefa(e.target.value) }} />
                  <Form.Text className="text-muted">
                    Descrição da Tarefa.
                  </Form.Text>
                </Form.Group>
              </Row>
              <Row mt={1}>
                <Col md={3}>  
                  <Form.Group className="mb-3" controlId="inicioTarefa">
                    <Form.Control type="date" value={inicioTarefa} onChange={e => { setInicioTarefa(e.target.value) }} sx={{
                        color:'rgba(0, 0, 0, 0.6)',
                        fontWeight: 400,
                        paddingLeft:'13px'
                      }}  />
                    <Form.Text className="text-muted">
                      Início da Tarefa.
                    </Form.Text>
                  </Form.Group>
                </Col>  
                <Col md={3}>  
                  <Form.Group className="mb-3" controlId="fimTarefa">
                    <Form.Control type="date" value={fimTarefa} onChange={e => { setFimTarefa(e.target.value) }} sx={{
                        color:'rgba(0, 0, 0, 0.6)',
                        fontWeight: 400,
                        paddingLeft:'13px'
                      }}  />
                    <Form.Text className="text-muted">
                      Fim da Tarefa.
                    </Form.Text>
                  </Form.Group>
                </Col>
                <Col md={3}>  
                  <Form.Group className="mb-3" controlId="recursoTarefa">
                    <Form.Select aria-label="Recurso" sx={{
                        color:'rgba(0, 0, 0, 0.6)',
                        fontWeight: 400,
                      }}
                      onChange={handleRecurso}
                      size="sm"
                      value={recursoTarefa}
                    >
                      <option>Selecione</option>
                      <option value={'Recurso 1'}>Recurso 1</option>
                      <option value={'Recurso 2'}>Recurso 2</option>
                      <option value={'Recurso 3'}>Recurso 3</option>
                    </Form.Select>
                  </Form.Group>
                </Col>
                <Col md={3}>  
                  <Form.Group className="mb-3" controlId="statusTarefa">
                    <Form.Select aria-label="Status" sx={{
                        color:'rgba(0, 0, 0, 0.6)',
                        fontWeight: 400,
                      }}
                      onChange={handleStatus}
                      size="sm"
                      value={statusTarefa}
                    >
                      <option>Selecione</option>
                      <option value={'Aguardando'}>Aguardando</option>
                      <option value={'Em Andamento'}>Em Andamento</option>
                      <option value={'Concluída'}>Concluída</option>
                    </Form.Select>
                  </Form.Group>                  
                </Col>
              </Row>
            </Form>
          </Card.Text>
          <Row>
            <Col md="auto">
              <Button variant="success" onClick={handleEditar}>Salvar</Button>
            </Col>
            <Col md={1}>
              <Button variant="secondary" onClick={handleCloseEditar}>Cancelar</Button>  
            </Col>
          </Row>       
        </Card.Body>
      </Card>
    </Container>
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