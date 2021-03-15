package StoreandGetFile.DataBase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import StoreandGetFile.DataBase.exception.FileStorageException;
import StoreandGetFile.DataBase.exception.MyFileNotFoundException;
import StoreandGetFile.DataBase.model.DBFileModel;
import StoreandGetFile.DataBase.reprository.DBFileRepo;

@Service
public class DBFileStorageService {

	@Autowired
	private DBFileRepo repo;
	
	public DBFileModel storeData(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	
		try {
			
		if(fileName.contains("..")) {
			throw new FileStorageException("file name is invalid: "+fileName);
		}
		
		DBFileModel model = new DBFileModel(fileName, file.getContentType(), file.getBytes());
		return repo.save(model);
		
		}catch(Exception ex) {
			throw new FileStorageException("Could not save file "+fileName+" please try again",ex);
		}
	}
	
	public DBFileModel getData(String fileId) {
		return repo.findById(fileId)
				.orElseThrow(()-> new MyFileNotFoundException("file Not found"));
	}
}
