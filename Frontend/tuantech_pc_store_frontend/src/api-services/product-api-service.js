import axios from "axios";

function getAllProduct() {
    const result = axios
        .get("http://localhost:8080/components/all")
        .then((response) => {
            console.log(response.data);
            return response.data;
        })
        .catch((error) => {
            console.log(error);
        });
    return result;
}

function getDetailProduct(id) {
    const result = axios
        .get(`http://localhost:8080/components/detail/${id}`)
        .then((response) => {
            console.log(response.data);
            return response.data;
        })
        .catch((error) => {
            console.log(error);
        });
    return result;
}

function loadProductImage(category, type, group, imageFileName) {
    const result = axios
        .get(
            `http://localhost:8080/components/image/${category}/${type}/${group}/${imageFileName}`
        )
        .then((response) => {
            console.log(response.data);
            return response.data;
        })
        .catch((error) => {
            console.log(error);
        });
    return result;
}

export default [getAllProduct, getDetailProduct, loadProductImage];
