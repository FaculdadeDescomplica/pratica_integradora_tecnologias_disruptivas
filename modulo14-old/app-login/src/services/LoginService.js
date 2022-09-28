{/*Servico responsavel pelo login. Posteriormente, devera ser alterado para chamar uma API externa para validacao das credenciais */}
const LoginService = async (userName , password) => {
  console.log(`Login Service - userName: ${userName} - Password: ${password}`);

  // Dados fake (mockup) do Usuario
  const database = [
    {
      username: "user1",
      password: "pass1"
    },
    {
      username: "user2",
      password: "pass2"
    }
  ];  

  try{

    // Localiza o username no array database (dados fake)
    const userData = database.find((user) => user.username === userName);

    //console.log('userData no LoginService: ' + JSON.stringify(userData));

    // Utiliza Promise (para uso de uma funcao async) com setTimeout (para simular o tempo de demora de resposta de uma API externa)
    return new Promise(r => setTimeout(r, 3000, userData));

  }catch(error){
    console.log('Erro ao realizar login: ' + JSON.stringify(error));
    return false;
  }
}

export {LoginService};