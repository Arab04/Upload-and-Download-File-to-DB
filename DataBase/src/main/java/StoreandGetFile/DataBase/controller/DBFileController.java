package StoreandGetFile.DataBase.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import StoreandGetFile.DataBase.model.DBFileModel;
import StoreandGetFile.DataBase.payloads.DBFileResponse;
import StoreandGetFile.DataBase.service.DBFileStorageService;

@RestController
public class DBFileController {

	
	@Autowired
	private DBFileStorageService service;
	
	@PostMapping("/uploadFile")
	public DBFileResponse storeFile(@RequestParam("file") MultipartFile file) {
		DBFileModel dbFile = service.storeData(file);
		
		String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(dbFile.getId())
				.toUriString();
		
		return new DBFileResponse(dbFile.getFileName(), downloadUri,
				file.getContentType(),file.getSize());
	}
	
	@PostMapping("/uploadMultipleFile")
	public List<DBFileResponse> uploadFiles(@RequestParam("files") MultipartFile [] files) {
		return Arrays.asList(files)
				.stream()
				.map(file->storeFile(file))
				.collect(Collectors.toList());
	}
	
	
}
