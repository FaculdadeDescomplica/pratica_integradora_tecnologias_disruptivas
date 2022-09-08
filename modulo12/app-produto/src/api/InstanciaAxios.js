import axios from 'axios';

const InstanciaAxios = axios.create({
  baseURL: "https://fakestoreapi.com",
});

export default InstanciaAxios;