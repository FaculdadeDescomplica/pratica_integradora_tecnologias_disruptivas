import axios from 'axios';

const InstanciaAxios = axios.create({
  baseURL: "http://192.168.220.128:18080",
});

export default InstanciaAxios;