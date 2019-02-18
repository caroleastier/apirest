package com.myaudiolibrairy.apirest.repository;

import com.myaudiolibrairy.apirest.Model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByName(String name);

    Artist findById(Long id);

    //List<Artist> findByNameIsContaining (String name);


    Page<Artist> findByNameIsContaining(String name, Pageable pageable);


    List<Artist> findAll();



}
