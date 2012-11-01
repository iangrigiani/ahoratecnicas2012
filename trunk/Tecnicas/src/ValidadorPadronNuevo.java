
class ValidadorPadronNuevo extends ValidadorParametro {
    @Override
  public String getDescripcion() {
    return "PADRON";
  }

    @Override
  public boolean validar(String parametro) {
    try{
        if(Integer.parseInt(parametro)>0)
            return true;
        return false;
    }catch(NumberFormatException e){
        return false;
    }
  }

}
