import Header from "./components/header";
import ListaProduto from "./pages/Produto/ListaProduto";
import CadastroProduto from "./pages/Produto/CadastroProduto";

const App = () => {
  return (
    <div className="App">
      <Header/>
      <ListaProduto />
      <CadastroProduto />
    </div>
  );
}

export default App;
