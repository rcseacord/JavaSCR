package err10j;

public class Book
{
    private String _Title;

    public Book(String title) {
        if (title == null) {
            throw new IllegalArgumentException("title parameter is null"); //$NON-NLS-1$
        }
        setTitle(title);
    }

	public String getTitle() {
		return this._Title;
	}

	public void setTitle(String _Title) {
		this._Title = _Title;
	}
}
