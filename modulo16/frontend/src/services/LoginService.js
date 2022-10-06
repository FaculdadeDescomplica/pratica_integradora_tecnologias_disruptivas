import { useState } from "react";
import InstanciaAxios from "../api/InstanciaAxios";

const LoginService = async (userName , password) => {
  //const[userData, setUserData] = useState('');
  console.log(`Login Service - userName: ${userName} - Password: ${password}`);
  try{

    // Faz a autenticacao no backend, recebendo o JWT em caso de sucesso
    const jwt = await InstanciaAxios.post(
      `/auth/login`,{
        email: userName,
        password: password
      }
    );
    /*.then(result => { 
      console.log('Login Service Result data: ' + JSON.stringify(result.data));
      //setUserData(result.data);
    });*/

    //console.log('userData no LoginService: ' + JSON.stringify(userData));

    //return userData;

    return jwt.data;

  }catch(error){
    console.log('Erro ao realizar login: ' + JSON.stringify(error));
    return false;
  }
}

export {LoginService};