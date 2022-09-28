import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }) => {
  const authUser = JSON.parse(localStorage.getItem('authUser')) || false;
  //console.log('authUser: ' + JSON.stringify(authUser));
  
  if (!authUser) {
      // Se nao encontrar o usuario no AsyncStorare (usuario nao logado) redireciona pra pag. de Login
      return <Navigate to="/login" />
  }

  // Encontrando o usuario, permite a navegacao pros demais componentes
  return children;
}

export { PrivateRoute };