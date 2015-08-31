package paris.velocafe.marketplace.entity;

public class Image {

	private Long idImage;
	private Long idProduit;
	private String fileName;
	private int ordre;
	private byte[] data;
	private Long size;
	private String type;

	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public interface Props {
		String idImage = "idImage";
		String idProduit = "idProduit";
		String fileName = "fileName";
		String ordre = "ordre";
		String data = "data";
		String size = "size";
		String type = "type";
	}
	
	
}
