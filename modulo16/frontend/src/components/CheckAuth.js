const CheckAuth = (location) => {
  if (localStorage.getItem("authUser")) {
    const jwt_Token_decoded = JSON.parse(localStorage.getItem("authUser"));
    
    //Armazena o tempo da sessao em milisegundos (60000 ml = 1 min)
    const tempoDuracaoSessao = 180000; 

    console.log(Date.now() + " > " + parseInt(jwt_Token_decoded.exp + tempoDuracaoSessao))

    //Se a data/hora atuais (em milisegundos) for maior que a data/hora setada para a sessao, 
    //  limpa o storage e desloga o usuario.
    //  Com o localStorage limpo, o usuario nao conseguira mais navegar pra outras paginas, sem antes 
    //  se logar novamente.
    if (Date.now() > parseInt(jwt_Token_decoded.exp + tempoDuracaoSessao)  ) {
      localStorage.clear();
      return false;
    }else{
      return true;
    } 
  }
}

export default CheckAuth;