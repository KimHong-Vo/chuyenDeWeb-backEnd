package com.web.bookStore.uploadImageModule;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface StorageService {
	String storeFile(MultipartFile file);

	Stream<Path> loadAll();

	byte[] readFileContent(String fileName);

	void deleteAllFiles();
}
