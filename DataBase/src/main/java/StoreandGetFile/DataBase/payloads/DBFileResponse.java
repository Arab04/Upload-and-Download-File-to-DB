package StoreandGetFile.DataBase.payloads;

import StoreandGetFile.DataBase.model.DBFileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DBFileResponse {

	private String fileName;
	
	private String fileDownloadUri;
	
	private String fileType;
	
	private Long size;
}
