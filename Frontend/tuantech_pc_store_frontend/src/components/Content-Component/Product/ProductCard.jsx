import React from "react";
import "./ProductCard.css";

function ProductCard({ productCardInfo }) {
    const {
        product_id,
        product_model,
        product_price,
        product_category_name,
        product_type_name,
        product_group_name,
        image_name
    } = productCardInfo;

    console.log(productCardInfo);

    const handleImageUrl = (pathVariables) => {
        const { category, type, group, imageFileName } = pathVariables;
        const imageUrl = `http://localhost:8080/collections/image/${category}/${type}/${group}/${imageFileName}`;
        return imageUrl;
    };

    return (
        <div className='product-card-container'>
            <div className='product-card-image-box'>
                <img
                    src={handleImageUrl({
                        category: product_category_name,
                        type: product_type_name,
                        group: product_group_name,
                        imageFileName: image_name
                    })}
                    alt=''
                />
            </div>

            <div className='product-card-info'>
                <p>{product_model}</p>
                <p>{product_price} $</p>
            </div>

            <div></div>
        </div>
    );
}

export default ProductCard;
