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
import { Container, Dropdown } from 'react-bootstrap';

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
          <a className="navbar-brand" href="/">
            <img src="https://e7.pngegg.com/pngimages/155/3/png-clipart-sota-roda-sommardrommar-concrete-construction-augers-product-supermarket-logo-text-supermarket.png" width="40" height="40" 
            className="d-inline-block align-top rounded-circle" alt=""></img>
             &nbsp;&nbsp;SManager
          </a>

          <Dropdown>  
            <Dropdown.Toggle variant="btn btn-secondary dropdown-toggle" id="dropdown-products">  
              Products
            </Dropdown.Toggle>  
            
            <Dropdown.Menu>  
              <Dropdown.Item href="/api/v1/productos">Catalog</Dropdown.Item>  
              <Dropdown.Item href="/api/v2/productos">Manage products</Dropdown.Item>  
            </Dropdown.Menu>  
          </Dropdown>  

          <Container className='p-4'>  
          <Dropdown>  
            <Dropdown.Toggle variant="btn btn-secondary dropdown-toggle" id="dropdown-products">  
              Orders
            </Dropdown.Toggle>  
            
            <Dropdown.Menu>  
              <Dropdown.Item href="/api/v2/pedidos">Create order</Dropdown.Item>  
              <Dropdown.Item href="/api/v2/detallepedidos">Check order</Dropdown.Item>  
            </Dropdown.Menu>  
          </Dropdown>  
          </Container> 

          <Dropdown>  
            <Dropdown.Toggle variant="btn btn-secondary dropdown-toggle" id="dropdown-products">  
              User
            </Dropdown.Toggle>  
            
            <Dropdown.Menu>  
              <Dropdown.Item href="/">Login</Dropdown.Item>  
              <Dropdown.Item href="/logout">Logout</Dropdown.Item>  
            </Dropdown.Menu>  
          </Dropdown> 
          
      </nav>
    </header>
    
  );
}
export default App;