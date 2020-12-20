package matrizdlistaligadaforma2;

public class MatrizListaligadaforma2 {

    NodoDoble nodoConfiguracion; // en el libro se llama mat

    public MatrizListaligadaforma2(int numeroFilas, int numeroColumnas) {
        CrearNodosC(numeroFilas, numeroColumnas);
    }

    private void CrearNodosC(int numeroFilas, int numeroColumnas) {
        Tripleta t = new Tripleta(numeroFilas, numeroColumnas, 0);
        nodoConfiguracion = new NodoDoble(t);
        NodoDoble cabeza = new NodoDoble(null);
        cabeza.setLigaC(cabeza);
        cabeza.setLigaF(cabeza);

        nodoConfiguracion.setLigaC(cabeza);
        nodoConfiguracion.setLigaF(cabeza);
    }
    
    public void setCelda(int fila, int columna, int valor) {
        Tripleta nuevoTripletaRegistro = new Tripleta(fila, columna, valor);
        setCelda(nuevoTripletaRegistro);
    }
    
    public void setCelda(Tripleta t) {
        NodoDoble nuevoNodo = new NodoDoble(t);
        conectarFilas(nuevoNodo);
        conectarColumnas(nuevoNodo);
        int c = nodoConfiguracion.getT().getV();
        nodoConfiguracion.getT().setV(c++);
    }
    
    private void conectarFilas(NodoDoble nuevoNodo) {
        int filaNuevoNodo = nuevoNodo.getT().getF();
        int columnaNuevoNodo = nuevoNodo.getT().getC();

        NodoDoble cabeza = this.getCabeza();
        NodoDoble ultimo = cabeza;
        NodoDoble nodoRecorrido = cabeza.getLigaF();

        boolean siDebeInsertar = true;

        while (nodoRecorrido != null && nodoRecorrido != cabeza) {
            if (nodoRecorrido.getT().getF() < filaNuevoNodo) {
                ultimo = nodoRecorrido;
                nodoRecorrido = nodoRecorrido.getLigaF();
            } else {
                break;
            }
        }

        while (nodoRecorrido != null && nodoRecorrido != cabeza) {
            if (nodoRecorrido.getT().getF() == filaNuevoNodo) {
                if (nodoRecorrido.getT().getC() < columnaNuevoNodo) {
                    ultimo = nodoRecorrido;
                    nodoRecorrido = nodoRecorrido.getLigaF();
                } else if (nodoRecorrido.getT().getC() == columnaNuevoNodo) {
                    siDebeInsertar = false;
                    nodoRecorrido.getT().setV(nuevoNodo.getT().getV());
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        if (siDebeInsertar) {
            ultimo.setLigaF(nuevoNodo);
            nuevoNodo.setLigaF(nodoRecorrido);
        }
    }
    
    private void conectarColumnas(NodoDoble nuevoNodo) {
        
        int filaNuevoNodo = nuevoNodo.getT().getF();
        int columnaNuevoNodo = nuevoNodo.getT().getC();

        
        NodoDoble cabeza = getCabeza();
        NodoDoble ultimo = cabeza;
        NodoDoble nodoRecorrido = cabeza.getLigaC();

        boolean siDebeInsertar = true;

        while (nodoRecorrido != null && nodoRecorrido != cabeza) {
            if (nodoRecorrido.getT().getC() < columnaNuevoNodo) {
                ultimo = nodoRecorrido;
                nodoRecorrido = nodoRecorrido.getLigaC();
            } else {
                break;
            }
        }

        while (nodoRecorrido != null && nodoRecorrido != cabeza) {
            if (nodoRecorrido.getT().getC() == columnaNuevoNodo) {
                if (nodoRecorrido.getT().getF() < filaNuevoNodo) {
                    ultimo = nodoRecorrido;
                    nodoRecorrido = nodoRecorrido.getLigaC();
                } else if (nodoRecorrido.getT().getF() == filaNuevoNodo) {
                    siDebeInsertar = false;
                    nodoRecorrido.getT().setV(nuevoNodo.getT().getV());
                    break;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        if (siDebeInsertar) {
            ultimo.setLigaC(nuevoNodo);
            nuevoNodo.setLigaC(nodoRecorrido);
        }
    }
    
    private NodoDoble getCabeza() {
        return nodoConfiguracion.getLigaC();
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        // Obtengo la configuración de la matriz, fr y cr y la cantidadValores
        Tripleta configuracion = nodoConfiguracion.getT();
        int fr = configuracion.getF();
        int cr = configuracion.getC();
        // Imprimir una línea con encabezado de las columnas
        cadena.append("\t");
        for (int i = 0; i < cr; i++) {
            cadena.append(i + "\t");
        }
        cadena.append("\n");

        NodoDoble nodoCabeza = nodoConfiguracion.getLigaF();
        NodoDoble nodoRecorrido = nodoCabeza.getLigaF();
        // Recorrido por una matriz virtual m x n
        for (int fv = 0; fv<fr; fv++) {
            cadena.append(fv + "\t");
            for (int cv = 0; cv < cr; cv++) {
                if (nodoRecorrido != null && nodoRecorrido != nodoCabeza) {
                    Tripleta triMo = nodoRecorrido.getT();
                    int ft = triMo.getF();
                    int ct = triMo.getC();
                    if (fv == ft) {
                        if (cv < ct) {
                            cadena.append("0\t");
                        } else if (cv == ct) {
                            int vt = triMo.getV();
                            if (vt==1) {
                                cadena.append(vt + "\t");
                            } else {
                                cadena.append("0\t");
                            }
                            nodoRecorrido = nodoRecorrido.getLigaF();
                        } else {
                            cadena.append("ERROR x COLUMNAS se paso!!!!");
                        }
                    } else {
                        cadena.append("0\t");
                    }
                } else {
                    cadena.append("0\t");
                }
            }
            cadena.append("\n");
        }
        return cadena.toString();
    }
    
}
/*if (nodoRecorrido != null && nodoRecorrido != nodoCabeza) {
                    Tripleta triMo = nodoRecorrido.getT();
                    int ft = triMo.getF();
                    int ct = triMo.getC();
                    if (fv == ft) {
                        if (cv < ct) {
                            cadena.append("0\t");
                        } else if (cv == ct) {
                            Object vt = triMo.getV();
                            if (vt != null) {
                                cadena.append(vt + "\t");
                            } else {
                                cadena.append("ERROR x COLUMNAS!!!!");
                            }
                            nodoRecorrido = nodoRecorrido.getLigaF();
                        } else {
                            cadena.append("ERROR x COLUMNAS se paso!!!!");
                        }
                    } else {
                        cadena.append("0\t");
                    }
                } else {
                    cadena.append("0\t");
                }*/