package ConverterPackage;

public class TypeFileClass{

    public enum typeFile {
        PDF,
        DOCX,
        TXT,
        CSV;
    }

    private typeFile type;

    public TypeFileClass() {
        this.type = null;
    }

    public typeFile getType() {
        return type;
    }

    public void setType(typeFile type) {
        this.type = type;
    }
}
