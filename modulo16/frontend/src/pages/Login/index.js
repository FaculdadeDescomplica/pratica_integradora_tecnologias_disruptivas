import React, {useState} from 'react';
import { FormControl, 
  Input,
  FormHelperText
} from '@mui/material';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import { LoginService } from '../../services/LoginService';
import { useNavigate } from 'react-router-dom';

import ButtonLoading from '../../components/ButtonLoading';

const Login = () => {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessages, setErrorMessages] = useState({});
  const [showRenderErrorMessage, setShowRenderErrorMessage] = useState(false);
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();
  
  const errors = {
    credenciais: "Usuário e/ou Senha inválidos!",
  };
 
  const handleSubmit = async () => {

    //Modifica o state para nao exibir a msg de erro (limpa a msg qdo tenha sido exibida anteriormente)
    setShowRenderErrorMessage(false);

    //Modifica os states para exibir o botao de "carregando"
    if (!loading) {
      setSuccess(false);
      setLoading(true);
    }  
     
    //Faz a chamada do servico de Login passando usuario e senha
    var userData = await LoginService(userName, password);

    //Exibe no console os dados do usuario retornado no servico acima ou undefined caso o mesmo nao exista
    console.log('userData: ' + JSON.stringify(userData));

    // Se nao encontrou o usuario ou se os dados estao incorretos
    if (!userData) {
      
      //Modifica o state para exibir a msg de erro de credenciais invalidas
      setShowRenderErrorMessage(true);
      setErrorMessages({ name: "credenciais", message: errors.credenciais });
      
      //Modifica os states para inibir o botao de "carregando"
      setSuccess(false);
      setLoading(false);

    } else {

      //Modifica os states para inibir o botao de "carregando"
      setSuccess(true);
      setLoading(false);

      //armazena os dados do usuario (nesse caso, o username) e a data atual para controle da expiracao da sessao no local storage
      //  CUIDADO: a senha nao deve ser armazenada
      localStorage.setItem('authUser', JSON.stringify(
        {jwt: userData["jwt-token"], exp:Date.now()}
      ));

      //navega para a Home
      navigate("/")
    }
  };

  {/*Componente de exibicao da msg de erro. Sua exibicao e controlada por states */}
  const renderErrorMessage = (name) =>
    showRenderErrorMessage && name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
    );  

  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="center"
      minHeight="100vh"
    >
      <Grid
        container
        spacing={0}
        direction={'column'}
        align={'center'}
        justifyItems="center"
        sx={{boxShadow:2, padding:5, width:250}}
      >
        <Grid item>
          <FormControl>
            <Input id="usuario" aria-describedby="usuario_helper_text" value={userName} onChange={e => { setUserName(e.target.value) }} required />
            <FormHelperText id="usuario_helper_text">Usuário</FormHelperText>
          </FormControl>
        </Grid>
        <Grid item >
          <FormControl>
            <Input id="senha" aria-describedby="senha_helper_text" type='password' value={password} onChange={e => { setPassword(e.target.value) }} required />
            <FormHelperText id="senha_helper_text">Senha</FormHelperText>
          </FormControl>
        </Grid>
        <Grid item sx={{marginTop:2}}>
          {/* Utiliza o componente botao customizado, que quando clicado exibe o loading/carregando*/}
          <ButtonLoading text={'Login'} onclickFunction={handleSubmit} buttonVariant={'outlined'} loadingState={loading} successState={success} />
        </Grid>
        <Grid item sx={{fontSize: 14, color:'red', marginTop:2}}>
          {renderErrorMessage("credenciais")}
        </Grid>
      </Grid>
    </Box>
  );
}

export default Login;