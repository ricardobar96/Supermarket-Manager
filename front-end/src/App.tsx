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
import 'bootstrap/dist/css/bootstrap.min.css';

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
      <h1>Supermarket Manager</h1>
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
    <header className="container-fluid bg-dark shadow">
        <nav className="navbar navbar-expand-lg navbar-dark mx-5 px-4 py-2">
          <Link to="/" className="navbar-brand text-white"> Home </Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#list"
            aria-controls="list" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="dropdown show">
            <a className="btn btn-secondary dropdown-toggle" role="button" id="dropdownLinkProducts" 
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Products
            </a>

            <div className="dropdown-menu" aria-labelledby="dropdownLinkProducts">
              <Link to="/api/v1/productos" className="dropdown-item">Catalog</Link>
              <Link to="/api/v2/productos" className="dropdown-item">Manage products</Link>
            </div>
          </div>

          <div className="dropdown show">
            <a className="btn btn-secondary dropdown-toggle" role="button" id="dropdownLinkOrders" 
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Orders
            </a>

            <div className="dropdown-menu" aria-labelledby="dropdownLinkOrders">
              <Link to="/api/v2/pedidos" className="dropdown-item">Order a product</Link>
              <Link to="/api/v2/detallepedidos" className="dropdown-item">Check your order</Link>
            </div>
          </div>

          <Link to="/" className="navbar-brand text-white"> Login </Link>
          <Link to="/logout" className="navbar-brand text-white"> Logout </Link>
          
      </nav>
    </header>
    
  );
}
export default App;