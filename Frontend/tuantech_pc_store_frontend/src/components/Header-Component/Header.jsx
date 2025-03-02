import React from "react";
import "./Header.css";

function Header() {
    return (
        <div className='header-container'>
            <div className='header-content-1'>
                <p>
                    TuanTech PC Store super happy to welcome custom to our store
                </p>
            </div>
            <div className='header-content-2'>
                <div className='image-box'>
                    <img
                        src='https://file.hstatic.net/200000680123/file/tt_logo_black_779867cb0507407a8433688e203d4a2e.png'
                        alt='Logo'
                        width={130}
                        height={100}
                    />
                </div>

                <div className='search-box'>
                    <input type='text' />
                </div>

                <div className='content-box'>
                    <p>Content Box</p>
                </div>
            </div>
        </div>
    );
}

export default Header;
