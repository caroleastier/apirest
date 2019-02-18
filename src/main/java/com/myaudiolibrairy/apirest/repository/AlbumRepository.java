package com.myaudiolibrairy.apirest.repository;

import com.myaudiolibrairy.apirest.Model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
