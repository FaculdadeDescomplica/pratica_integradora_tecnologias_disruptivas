import * as React from 'react';
import Box from '@mui/material/Box';
import CircularProgress from '@mui/material/CircularProgress';
import { green } from '@mui/material/colors';
import Button from '@mui/material/Button';

{/* Esse componente, botao com loading, podera ser utilizado sempre que uma acao do usuario implicar no carregamento de dados*/}
const ButtonLoading = ({text, onclickFunction, buttonVariant, loadingState, successState}) => {
  {/* De acordo com o state sucessState, sobrescreve o estilo do botao*/}
  const buttonSx = {
    ...(successState && {
      bgcolor: green[500],
      '&:hover': {
        bgcolor: green[700],
      },
    }),
  };

  return (
    <Box sx={{ display: 'flex', alignItems: 'center', justifyContent:"center" }}>
      <Box sx={{ m: 1, position: 'relative' }}>
        <Button
          variant={buttonVariant}
          sx={buttonSx}
          disabled={loadingState}
          onClick={onclickFunction}
        >
          {text}
        </Button>
        {/* Enquanto o state loadingState for true, mostra o loading/carregando*/}
        {loadingState && (
          <CircularProgress
            size={24}
            sx={{
              color: green[500],
              position: 'absolute',
              top: '50%',
              left: '50%',
              marginTop: '-12px',
              marginLeft: '-12px',
            }}
          />
        )}
      </Box>
    </Box>
  );
}

export default ButtonLoading;