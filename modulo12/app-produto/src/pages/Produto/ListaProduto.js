import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import InstanciaAxios from '../../api/InstanciaAxios';

const ListaProduto = () => {
    const[produto, setProduto] = useState([]);

    useEffect(() => {
      getProdutos();
    }, []);

    const getProdutos = async () => {
      InstanciaAxios.get(
            `/products`
          )
          .then(result => { 
            console.log('result data: ' + JSON.stringify(result.data));
            setProduto(result.data);
          });
    }

    const colunas = [
      { field: 'id', headerName: 'ID', width: 70 },
      { field: 'title', headerName: 'Título', width: 130 },
      { field: 'price', headerName: 'Preço', width: 130, type: 'number' },
      { field: 'category', headerName: 'Categoria', width: 130 },
      { field: 'description', headerName: 'Descrição', width: 130 },
      { field: 'image', headerName: 'Imagem', width: 130 }
    ];

    return (
      <div style={{ height: 400, width: '100%' }}>
        <DataGrid
          rows={produto}
          columns={colunas}
          pageSize={5}
          rowsPerPageOptions={[5]}
          checkboxSelection
        />
      </div>    
    );
}

export default ListaProduto;