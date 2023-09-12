import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps{}
interface IState{productos ?: Array<Supermercado.Producto>;}

declare module Supermercado {

    export interface Producto {
        idproducto: number;
        nombre: string;
        preciounidad: number;
        stock: number;
      }
  
  }

export const Productos_v1 = () => {
    const [productos,setProducto] = useState<IState>();
    const ip:string = "localhost";
    const puerto:number = 8080;
    const rutaBase:string = "http://"+ip+":"+puerto;
    const rutaProductos:string = rutaBase+"/api/v1/productos"; 

    useEffect(() => {
        const getProducto = async()=> {
            let ruta = rutaProductos;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setProducto({ productos: respuesta.data });
        }
        getProducto();
    }, []);

    return (
        <main className="w-50 mx-auto rounded" style={{background: "#d6eaf8"}}>
            <section className="m-auto mt-5 text-center">
                <h1 className="lead display-6 text-dark py-3 fw-bold">Catalog</h1>
                <table className="table table-bordered table-light table-hover mb-2">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Unit price</th>
                            <th>Stock</th>
                        </tr>
                    </thead>
                    <tbody>
                        {productos?.productos?.map((item:Supermercado.Producto) => (
                        <tr key={item.idproducto}>
                            <td><Link to={{pathname:"/api/v1/producto/" + item.idproducto}}>{item.idproducto}</Link></td>
                            <td>{item.nombre}</td>
                            <td>{item.preciounidad}</td>
                            <td>{item.stock}</td>
                        </tr>
                        ))}
                    </tbody>
                </table>
                
            </section>
        </main>
        );
    }
    export default Productos_v1;