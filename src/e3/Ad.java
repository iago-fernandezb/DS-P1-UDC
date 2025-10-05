package e3;

public class Ad {

    private String agencia;
    private AdType tipoA;
    private Property propiedad;
    private double precio;

    public Ad(String agencia, Property propiedad, AdType tipoA, double precio){
        this.agencia = agencia;
        this.tipoA = tipoA;
        this.propiedad = propiedad;
        this.precio = precio;
    }
    public Ad(Ad anuncio){
        this.agencia = anuncio.agencia;
        this.tipoA = anuncio.tipoA;
        this.propiedad = anuncio.propiedad;
        this.precio = anuncio.precio;
    }


    public boolean isPropertyEqual(Ad otroAnuncio) {
        return this.propiedad.equals(otroAnuncio.propiedad);
    }

    public boolean isPriceNormal(){
        double precioExcesivo;
        if(this.tipoA == AdType.PURCHASE){
            if(this.propiedad.tamano() != 0 && this.precio != 0 ) {
                precioExcesivo = 4000 * this.propiedad.tamano();
                return precioExcesivo > this.precio;
            }
            else return true;
        } else {
            if(this.propiedad.tamano() != 0 && this.precio != 0 ) {
                precioExcesivo = 200 * this.propiedad.tamano();
                return precioExcesivo > this.precio;
            }
            else return true;
        }
    }

    public double priceMetersEuros() throws ArithmeticException {
        if(this.propiedad.tamano() != 0 && this.precio != 0 ) {
            return this.precio / this.propiedad.tamano();
        }
        else {
            throw new ArithmeticException();
        }
    }


    public void dropPrice(double porcentaje) throws IllegalArgumentException {
        if (porcentaje > 0 && porcentaje < 100) {
            this.precio -= this.precio * (porcentaje / 100);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public double getPriceInEuros(){
        return this.precio;
    }

    @Override
    public String toString() {
        return "Agencia: " + agencia + "\n" +
                "Tipo de anuncio: " + tipoA + "\n" +
                "Propiedad: " + propiedad.toString() + "\n" +
                "Precio: " + precio;
    }
}
