import React, {useState, useEffect} from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import { useNavigate, useLocation } from 'react-router-dom';
import CheckAuth from './CheckAuth';

import { ListaMenu } from '../routes/RoutesNavegacao';

{/*Componente para exibicao da Barra no topo da aplicacao (appbar) */}
const Header = () => {
  const navigate = useNavigate();
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const location = useLocation();

  //O hook abaixo sera executado sempre que o usuario mudar de pagina.
  //  A cada mudanca, sera verificado, no localstorage, se o token/autenticacao expirou.
  //  Em caso positivo, o usuario sera deslogado do sistema
  useEffect(() => {
    console.log('location: ' + JSON.stringify(location));
    //Chama o Componente Funcional para 
    //  verificar se a autenticacao expirou.
    //  Em caso positivo, leva o usuario para
    //  a tela de login
    if(!CheckAuth(location))
      navigate("/login")
    
  }, [location]);

  const handleLogout = () => {
    //deleta os dados do usuario no local storage
    localStorage.removeItem('authUser');

    //navega para a pag. de Login
    navigate("/login");
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
    <AppBar position="static">
      <Toolbar>
        <IconButton
          size="large"
          edge="start"
          color="inherit"
          aria-label="menu"
          sx={{ mr: 2 }}
          onClick={() => setIsDrawerOpen(true)}
        >
          <MenuIcon />
        </IconButton>
        {/*Inclui o componente que contem as opcoes do menu drawer (cujas opcoes estao definidas nas rotas) */}
        <ListaMenu isDrawerOpen={isDrawerOpen} setIsDrawerOpen={setIsDrawerOpen} />
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Aplicação
        </Typography>
        <Button color="inherit" onClick={handleLogout}><ExitToAppIcon/></Button>
      </Toolbar>
    </AppBar>
  </Box>
 );
}
 
export default Header;