import {useState} from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import Drawer from "@mui/material/Drawer";
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import { useNavigate } from 'react-router-dom';

import { PrivateRoute } from "./PrivateRoute";
import Header from "../components/Header";
import Home from "../pages/Home";
import ListarTarefa from '../pages/Tarefa/ListarTarefa';
import Login from "../pages/Login";

{/*Componente react-router-dom contendo as rotas da aplicacao - privadas/publicas */}
const RoutesNavegacao = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
              <PrivateRoute>
                  <Header />
                  <Home />
              </PrivateRoute>
          }
        />
        <Route
          path="/tarefa"
          element={
              <PrivateRoute>
                  <Header />
                  <ListarTarefa />
              </PrivateRoute>
          }
        />
        <Route path="/login" element={<Login />} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>  
  );
}

{/*Componente contendo as opcoes do menu drawer correspondentes as rotas da aplicacao */}
const ListaMenu = ({isDrawerOpen, setIsDrawerOpen}) => {
  const [selectedIndex, setSelectedIndex] = useState();
  const navigate = useNavigate();

  const handleListItemClick = (event, navigationTo, index) => {
    //altera o state para marcar a opcao selecionada no menu
    setSelectedIndex(index);
    
    //Fecha o drawer
    setIsDrawerOpen(false)

    //navega para a pagina selecionada
    navigate(navigationTo);
  };
  return (
    <Drawer open={isDrawerOpen} onClose={() => setIsDrawerOpen(false)}>
      <List sx={{width:250}}>
        <ListItemButton
          selected={selectedIndex === 0}
          onClick={(event) => handleListItemClick(event, '/', 0)}
        >
          <ListItemText primary="Home" />
        </ListItemButton>
        <ListItemButton
          selected={selectedIndex === 1}
          onClick={(event) => handleListItemClick(event, 'tarefa', 1)}
        >
          <ListItemText primary="ListarTarefa" />
        </ListItemButton>
      </List>
    </Drawer>
  );
}

export {RoutesNavegacao, ListaMenu};