import React, { useEffect, useState } from "react";
import {
    getAllProduct,
    getDetailProduct,
    loadProductImage
} from "../../api-services/product-api-service";
import ProductCard from "./Product/ProductCard";

function Content() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        getAllProduct()
            .then((data) => {
                console.log(data.data);
                setProducts(data.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    const handleImageUrl = (pathVariables) => {
        const { category, type, group, imageFileName } = pathVariables;
        const imageUrl = `http://localhost:8080/collections/image/${category}/${type}/${group}/${imageFileName}`;
        return imageUrl;
    };
    const convertStringToJsonObj = (str) => {
        const productSpecificationsJsonObj = JSON.parse(str);
        console.log(productSpecificationsJsonObj);
        return productSpecificationsJsonObj;
    };

    return (
        <>
            <h1>All Product Show Here</h1>

            {products.map((product, index) => {
                const productDetailObj = convertStringToJsonObj(
                    product.specifications
                );
                const productCardInfo = {
                    product_id: product.product_id,
                    product_model: product.product_model,
                    product_price: product.product_price,
                    product_category_name: product.product_category_name,
                    product_type_name: product.product_type_name,
                    product_group_name: product.product_group_name,
                    image_name: product.image_name
                };
                return (
                    <ProductCard
                        productCardInfo={productCardInfo}
                    ></ProductCard>
                );
            })}
        </>
    );
}

export default Content;
