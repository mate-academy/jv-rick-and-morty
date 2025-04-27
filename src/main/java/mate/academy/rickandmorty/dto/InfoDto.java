package mate.academy.rickandmorty.dto;

import java.util.Objects;

public class InfoDto {

    private int count;
    private int pages;
    private String next;
    private String prev;

    public InfoDto() {
    }

    public InfoDto(int count, int pages, String next, String prev) {
        this.count = count;
        this.pages = pages;
        this.next = next;
        this.prev = prev;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "InfoDto{"
                + "count=" + count
                + ", pages=" + pages
                + ", next='" + next + '\''
                + ", prev='" + prev + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InfoDto)) {
            return false;
        }
        InfoDto infoDto = (InfoDto) o;
        return count == infoDto.count
                && pages == infoDto.pages
                && Objects.equals(next, infoDto.next)
                && Objects.equals(prev, infoDto.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, pages, next, prev);
    }
}
