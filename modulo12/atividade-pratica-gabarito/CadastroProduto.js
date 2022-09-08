import React, {useState} from 'react';
import { FormControl, Input } from '@mui/material';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import InstanciaAxios from '../../api/InstanciaAxios';

const CadastroProduto = () =>{
  const [title, setTitle] = useState('');
  const [price, setPrice] = useState();
  const [description, setDescription] = useState('');
  const [image, setImage] = useState('');
  const [category, setCategory] = useState('');
  
  const handleSalvar = () => {
    console.log(`title: ${title} \n 
                 price: ${price} \n 
                 description: ${description} \n 
                 image: ${image} \n 
                 category: ${category}`);

    InstanciaAxios.post(
    `/products`,{
        'title': title,
        'price': price,
        'description': description,
        'image': image,
        'category': category
      }
    )
    .then(result => { 
      console.log('result data: ' + JSON.stringify(result.data));
    })
    .catch(function (error) {
      alert('Ocorreu um erro ao tentar cadastrar o produto - ' + error);
      console.error('Ocorreu um erro ao tentar cadastrar o produto - ' + error);
    });

  };

  return(
    <Grid container spacing={2}>
      <Card sx={style}>
        <CardHeader
          title="Produtos"
          subheader="Cadastro de Produtos"
        /> 
        <CardContent sx={{
          width: '95%',
          maxWidth: '100%',
        }}>
          <Grid item xs={12}>
            <FormControl fullWidth>
              <Input value={title} placeholder="Title" onChange={e => { setTitle(e.target.value) }} />
            </FormControl>
          </Grid>
          <Grid item xs={12}>  
            <FormControl fullWidth>
              <Input value={price} placeholder="Price" onChange={e => { setPrice(e.target.value) }} />
            </FormControl>
          </Grid>
          <Grid item xs={12}>  
            <FormControl fullWidth>
              <Input value={description} placeholder="Description" onChange={e => { setDescription(e.target.value) }} />
            </FormControl>
          </Grid>
          <Grid item xs={12}>  
            <FormControl fullWidth>
              <Input value={image} placeholder="Image" onChange={e => { setImage(e.target.value) }} />
            </FormControl>
          </Grid>
          <Grid item xs={12}>  
            <FormControl fullWidth>
              <Input value={category} placeholder="Category" onChange={e => { setCategory(e.target.value) }} />
            </FormControl>
          </Grid>
          <Grid container spacing={2} pl={2} mt={2}>
            <Grid item xs={1}>
              <Button size="small" variant="contained" onClick={handleSalvar}>Salvar</Button>
            </Grid>  
          </Grid>  
        </CardContent>
      </Card>
    </Grid>
  );
}

const style = {
  margin: '10 2 0 0',
  width: '60%',
  bgcolor: 'background.paper',
  p: 4,
};

export default CadastroProduto;