package e3;

public record Property(
        PropertyType propiedad,
        String catastro,
        String direccion,
        String codigoPostal,
        int tamano,
        int nHabitaciones,
        int nBanos
) {

    @Override
    public String toString() {
        return propiedad.name() + "\n" +
                catastro + "\n" +
                direccion + "\n" +
                codigoPostal + "\n" +
                tamano + " meters, " +
                nHabitaciones + " rooms, " +
                nBanos + " bathrooms\n";
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Property other = (Property) obj;
        return this.catastro.equals(other.catastro);
    }

    @Override
    public int hashCode() {
        return catastro.hashCode();
    }

}
