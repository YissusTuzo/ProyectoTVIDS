package mx.uabcs.proyecto;

public class Horariosc {
    String day;
    String begin_time;
    String subject;
    String end_time;
    String installment;
    String teacher;

    public Horariosc(String dia, String horae,String horas, String materia, String maestro, String salon) {
        this.day = dia;
        this.begin_time = horae;
        this.subject= materia;
        this.end_time = horas;
        this.installment = salon;
        this.teacher = maestro;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
