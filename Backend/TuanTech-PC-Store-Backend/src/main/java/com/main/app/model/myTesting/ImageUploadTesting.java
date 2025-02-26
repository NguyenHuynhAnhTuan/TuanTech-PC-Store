package com.main.app.model.myTesting;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image_upload_testing_tbl")
public class ImageUploadTesting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content_type;
    private Long image_file_size;
    private String image_file_path;
    private String image_file_name;
}
