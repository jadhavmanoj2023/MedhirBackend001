package com.example.medhirBackend001.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    private String id;

    @NotBlank(message = "Company name cannot be empty")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    @Indexed(unique = true)  // Unique index for email
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    @Indexed(unique = true)  // Unique index for phone
    private String phone;

    @NotNull(message = "GST number cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9]{15}$", message = "GST must be a 15-digit alphanumeric code")
    private String gst;

    @NotBlank(message = "Registered address cannot be empty")
    private String regAdd;
}
