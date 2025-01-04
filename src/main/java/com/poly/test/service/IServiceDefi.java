package com.poly.test.service;

import com.poly.test.entities.Defi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceDefi {  // Changed IServiceProduit to IServiceDefi

    public void addDefi(Defi d, MultipartFile mf) throws IOException;  // Changed addProduit to addDefi
    public void deleteDefi(Long id);  // Changed deleteProduit to deleteDefi
    public void updateDefi(Defi d);  // Changed updateProduit to updateDefi
    public List<Defi> getAllDefis();  // Changed getAllProduits to getAllDefis
    public Page<Defi> getDefisByMc(String mc, Pageable p);  // Changed getProduitsByMc to getDefisByMc
    public Defi getDefiById(Long id);  // Changed getProduitById to getDefiById
    String saveImage(MultipartFile mf) throws IOException;  // Changed savImage to saveImage
    String saveImage2(MultipartFile mf) throws IOException;
}

