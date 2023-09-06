import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes, Navigate, useNavigate } from 'react-router-dom';
import Login from './Login';
import Productos_v1 from './v1/Producto_v1';
import Productos_v2 from './v2/Producto_v2';
import Logout from './Logout';
import ManageProducto_v1 from './v1/ManageProducto_v1';
import ManageProducto_v2 from './v2/ManageProducto_v2';
import Pedidos_v2 from './v2/Pedido_v2';
import ManagePedido_v2 from './v2/ManagePedido_v2';
import CreatePedido from './v2/CrearPedido';
import Detalles_v2 from './v2/Detalle_v2';
import ManageDetalle_v2 from './v2/ManageDetalle_v2';

interface IProps { }
interface IState { }

interface IProps {
  children: JSX.Element;
}
export const RequireAuth = ({ children }: IProps) => {
  let autorizado = localStorage.getItem("token");
  if (autorizado) {
    return children
  }
  return <Navigate to="/login" />
}

const App = () => {

  return (
    <BrowserRouter>
      <h1>Aplicación Supermercado</h1>
      <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/logout" element={<Logout />} />

        <Route path="/api/v1/productos" element={<Productos_v1 />} />
        <Route path="/api/v1/producto/:id" element={<ManageProducto_v1 />} />

        <Route path="/api/v2/productos" element={
          <RequireAuth >
            <Productos_v2 />
          </RequireAuth>
        } />
        <Route path="/api/v2/producto/:id" element={
          <RequireAuth >
            <ManageProducto_v2 />
          </RequireAuth>
        } />

        <Route path="/api/v2/pedidos" element={
          <RequireAuth >
            <Pedidos_v2 />
          </RequireAuth>
        } />
        <Route path="/api/v2/pedido/:id" element={
          <RequireAuth >
            <ManagePedido_v2 />
          </RequireAuth>
        } />
        
        <Route path="/api/v2/detallepedidos" element={
          <RequireAuth >
            <Detalles_v2 />
          </RequireAuth>
        } />
        <Route path="/api/v2/detallepedido/:id" element={
          <RequireAuth >
            <ManageDetalle_v2 />
          </RequireAuth>
        } />
        <Route path="/api/v2/crearPedido" element={
          <RequireAuth >
            <CreatePedido />
          </RequireAuth>
        } />
      </Routes>
    </BrowserRouter>
  );
}

const Navbar = () => {

  return (
    <nav>
      <Link to="/"> Login </Link> &nbsp;
      <Link to="/logout"> Logout </Link> &nbsp;
      <br />
      <br />
      <Link to="/api/v1/productos"> Productos (v1) </Link> &nbsp;
      <br />
      <br />
      <Link to="/api/v2/productos"> Productos (v2) </Link> &nbsp;
      <Link to="/api/v2/pedidos"> Pedidos (v2) </Link> &nbsp;
      <Link to="/api/v2/detallepedidos"> Detallepedidos (v2) </Link> &nbsp;
      <br />
      <br />
    </nav>
  );
}
export default App;