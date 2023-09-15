import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes, Navigate, useNavigate } from 'react-router-dom';
import Login from './user/Login';
import Productos_v1 from './v1/Producto_v1';
import Productos_v2 from './v2/Producto_v2';
import Logout from './user/Logout';
import ManageProducto_v1 from './v1/ManageProducto_v1';
import ManageProducto_v2 from './v2/ManageProducto_v2';
import Pedidos_v2 from './v2/Pedido_v2';
import ManagePedido_v2 from './v2/ManagePedido_v2';
import CreatePedido from './v2/CrearPedido';
import Detalles_v2 from './v2/Detalle_v2';
import ManageDetalle_v2 from './v2/ManageDetalle_v2';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';
import { Container, Dropdown } from 'react-bootstrap';
import logo from "./assets/logo.png";

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
  return <Navigate to="/api/v1/productos" />
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

const Navbar: React.FC = () => {
  const token = localStorage.getItem('token');

  return (
    <header className="container-fluid navbar-light bg-secondary shadow mb-5">
        <nav className="navbar navbar-expand-lg mx-5 px-4 py-2">
          <a className="navbar-brand px-5" href="/api/v1/productos">
            <img src={logo} width="50" height="50" 
            className="d-inline-block align-top rounded-circle" alt="img"></img>
             &nbsp;&nbsp;<span className='text-light fs-3'>SManager</span>
          </a>

          <Dropdown>  
            <Dropdown.Toggle variant="btn btn-light dropdown-toggle" id="dropdown-products">  
              <i className="fa fa-shopping-cart"></i>
            </Dropdown.Toggle>  
            
            <Dropdown.Menu>  
              <Dropdown.Item href="/api/v1/productos">Catalog</Dropdown.Item>  
              {token ? (
              <Dropdown.Item href="/api/v2/productos">Manage products</Dropdown.Item>  
              ) : (
                ""
              )}
            </Dropdown.Menu>  
          </Dropdown>  

          {token ? (
          <Container className='p-4'>  
          <Dropdown>  
            <Dropdown.Toggle variant="btn btn-light dropdown-toggle" id="dropdown-products">  
              <i className="fa fa-truck fa-fw"></i>
            </Dropdown.Toggle>  
            
            <Dropdown.Menu>  
              <Dropdown.Item href="/api/v2/pedidos">Create order</Dropdown.Item>  
              <Dropdown.Item href="/api/v2/detallepedidos">Check orders</Dropdown.Item>  
            </Dropdown.Menu>  
          </Dropdown>  
          </Container>
          ) : (
            <Container className='p-4'> 
            </Container>
          )} 

          <Dropdown>  
            <Dropdown.Toggle variant="btn btn-light dropdown-toggle" id="dropdown-products">  
              <i className="fa fa-user fa-fw"></i>
            </Dropdown.Toggle>  
            
            <Dropdown.Menu>  
              {!token ? (
              <Dropdown.Item href="/">Login</Dropdown.Item>  
              ) : (
                ""
              )} 
              {token ? (
              <Dropdown.Item href="/logout">Logout</Dropdown.Item>  
              ) : (
                ""
              )} 
            </Dropdown.Menu>  
          </Dropdown> 
          
      </nav>
    </header>
    
  );
}
export default App;