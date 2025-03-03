import "./App.css";

import Header from "./components/Header-Component/Header";
import Content from "./components/Content-Component/Content";
import Navbar from "./components/Navbar-Component/Navbar";

import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Header></Header>
            <Navbar></Navbar>

            <Routes>
                <Route path='/home' element={<Content />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
