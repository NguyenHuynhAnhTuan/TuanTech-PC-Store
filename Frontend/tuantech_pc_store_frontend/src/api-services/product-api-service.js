import axios from "axios";
import axiosClient from "./axiosClient";

const getAllProduct = async () => {
    try {
        const response = await axiosClient.get("/collections/all");
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

const getDetailProduct = async (id) => {
    try {
        const response = await axiosClient.get(`/collections/detail/${id}`);
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

const loadProductImage = async (query) => {
    const { category, type, group, imageFileName } = query;
    try {
        const result = await axiosClient.get(
            `/collections/image/${category}/${type}/${group}/${imageFileName}`
        );
        console.log(result.data);
        return result.data;
    } catch (error) {
        console.log(error);
    }
};

export { getAllProduct, getDetailProduct, loadProductImage };
