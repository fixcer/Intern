package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Magazine;
import com.example.demo.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class MagazineService {
    private MagazineRepository magazineRepository;

    @Autowired
    public MagazineService(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }


    public Magazine findById(Long id) {
        return magazineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Magazine", "id", id));
    }

    public List<Magazine> findAll() {
        List<Magazine> magazines = magazineRepository.findAll();
        return magazines;
    }

    public Magazine createMagazine(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    public Magazine updateMagazine(Long id, Magazine magazineDetails) {
        Magazine magazine = magazineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Magazine", "id", id));

        magazine.setTitle(magazineDetails.getTitle());
        magazine.setIsbn(magazineDetails.getIsbn());
        magazine.setPublicationDate(magazineDetails.getPublicationDate());
        magazine.setAuthors(magazineDetails.getAuthors());

        return magazineRepository.save(magazine);
    }

    public ResponseEntity<Object> deleteMagazine(Long id) {
        Magazine magazine = magazineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Magazine", "id", id));

        magazineRepository.delete(magazine);

        return ResponseEntity.ok().build();
    }
}
