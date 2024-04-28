# Main
Crea un menu amb switch que crida a la classe Supermercat per engegar el programa

# Supermercat
Classe utilitzada per introduir productes, passar per caixa i mostrar el carret de compra
### Paràmetres
- llistaProductes - Llista per guardar tots els productes
- llistaAliments - Llista per guardar productes de tipus Alimentacio
- llistaTextils - Llista per guardar productes de tipus Textil
- llistaElectronics - Llista per guardar productes de tipus Electronica
## Mètodes
- afegirProducte(String tipusProducte) -> Demana a l'usuari que introdueixi els detalls corresponents al tipus de producte especificat, com ara nom, preu, codi de barres, data de caducitat en cas de productes alimentaris, composició en cas de tèxtils i dies de garantia en cas de productes electrònics. Després, afegeix el producte tant a la llista específica corresponent com a una llista general
- passarCaixa() -> Agafa la llista de productes i crea un conjunt de HashSet per a cada tipus de producte, eliminant així els duplicats. Després, es verifica si la llista de productes únics està buida. En cas contrari, es mostra el tiquet amb els detalls dels productes. Finalment, es netegen les llistes de productes creades.
- mostrarCarret() -> Crea un HashMap on emmagatzema el codi de barres del producte com a clau i la seva quantitat com a valor. Després, recorre la llista general de productes i verifica per a cada un si ja existeix el codi de barres al carret. Si ja existeix, incrementa la quantitat en 1, si no, afegeix el codi de barres al carret amb una quantitat de 1. Seguidament, per a cada parell clau-valor del carret, mostra el nom del producte amb la quantitat corresponent, utilitzant el mètode buscarProductes() amb el codi de barres com a paràmetre. Per ultim, neteja el carret.
- buscarProductes(String codiBarres) - Crea un Optional i converteix llistaProductes a un stream, filtra per trobar el producte amb el mateix codi de barres que el passat amb codiBarres. Si troba el producte, retorna el seu nom.
- recollirExcepcions(String missatge) -> Crea dos Files, un al directori logs i un altre Exceptions.log dins d'aquest directori. Primer verifica si el directori existeix i el crea  fa el mateix per la creació del fitxer. Seguidament, crea un FileWriter amb l'arxiu de exceptions en mode append, escriu una línia amb la data i hora del registre de l'error i el missatge proporcionat. Finalment, tanca l'arxiu.

# Producte
Clase abstracte pare per generalitzar alguns paràmetres dels productes
```java
public Producte(String nom, float preu, String codibarres) throws IllegalArgumentException{
        this.nom = nom;
        this.preu = preu;
        this.codibarres = codibarres;
    }
```
### Paràmetres
- nom - Nom del producte
- preu - Preu del producte
- codiBarres - Codi de barres del producte

## Mètodes
- equals(Object o) -> Comprova si dos productes son iguals amb el seu preu i  el codi de barres
- hashCode() -> Retorna el hash code del producte (codi de barres)

# Alimentacio
Subclasse de Producte

### Paràmetres
- dataCaducitat - Data de caducitat

## Setters i Getters
- getDataCaducitat - Retorna la data de caducitat del producte alimentari
- setDataCaducitat - No s'utilitza
- getPreu - Sobreescriu al getter de la classe pare per calcular el seu preu amb la data actual i la de caducitat


# Texil
Subclasse de Producte que implementa l'interfície Comparable

### Paràmetres
- composicio - Composicio textils

## Setters i Getters
- getComposicio - Retorna la composició del producte textil
- setComposicio - No s'utilitza

## Mètodes
- compareTo(Textil t) -> Compara els productes tèxtils per la seva composició
- equals(Object o) -> Utilitzat per comprovar si dos productes textils son iguals pel codi de barres

# Electronica
Subclasse de Producte

### Paràmetres
- diesGarantia - Dies de la garantia

## Setters i Getters
- getDiesGarantia - Retorna els dies de garantia del producte electronic
- setDiesGarantia - No s'utilitza
- getPreu - Sobreescriu al getter de la classe pare per calcular el seu preu

# Maneig d'Errors
Si es produeix un error en el programa amb el metode recollirExcepcions els pasara al document logs per fàcil accés a aquests errors
```java
try {
            FileWriter escritor = new FileWriter(arxiuLogs, true);
            escritor.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " - " + missatge + '\n');
            escritor.close();
            throw new Exception("S'ha produit un error. Revisa el fitxer de logs");
        } catch (IOException e) {
            throw new RuntimeException(e);
```