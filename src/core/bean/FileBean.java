package core.bean;

public class FileBean
{
  private String id;
  private String text;
  private String path;
  private String absolutepath;
  private String size;
  private String type;
  private boolean isLeaf;

  public String getId()
  {
    return this.id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getText() {
    return this.text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public String getPath() {
    return this.path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public String getAbsolutepath() {
    return this.absolutepath;
  }
  public void setAbsolutepath(String absolutepath) {
    this.absolutepath = absolutepath;
  }
  public String getSize() {
    return this.size;
  }
  public void setSize(String size) {
    this.size = size;
  }
  public String getType() {
    return this.type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public boolean isLeaf() {
    return this.isLeaf;
  }
  public void setLeaf(boolean isLeaf) {
    this.isLeaf = isLeaf;
  }
}