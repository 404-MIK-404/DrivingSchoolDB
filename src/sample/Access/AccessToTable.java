package sample.Access;

public class AccessToTable {

    public enum Admin{



        JOB_TITLE("SELECT * FROM ДОЛЖНОСТЬ","INSERT INTO ДОЛЖНОСТЬ(ID_ДОЛЖНОСТЬ,НАИМЕНОВАНИЕ,ОКЛАД,ОБРАЗОВАНИЕ) VALUES (?,?,?,?)",
                "DELETE FROM ДОЛЖНОСТЬ WHERE ID_ДОЛЖНОСТЬ=?",
                "UPDATE ДОЛЖНОСТЬ SET НАИМЕНОВАНИЕ=?,ОКЛАД=?,ОБРАЗОВАНИЕ=? WHERE ID_ДОЛЖНОСТЬ=?"),
        STAFF("SELECT * FROM ПЕРСОНАЛ","INSERT INTO ПЕРСОНАЛ (ID_ПЕРСОНАЛА,ID_ДОЛЖНОСТЬ,ФИО,ПАСПОРТ,ОБРАЗОВАНИЕ,ТЕЛЕФОН) VALUES (?,?,?,?,?,?)",
                "DELETE FROM ПЕРСОНАЛ WHERE ID_ПЕРСОНАЛА=?",
                "UPDATE ПЕРСОНАЛ SET ID_ДОЛЖНОСТЬ=?,ФИО=?,ПАСПОРТ=?,ОБРАЗОВАНИЕ=?,ТЕЛЕФОН=? WHERE ID_ПЕРСОНАЛА=?"),
        RATE("SELECT * FROM ТАРИФ","INSERT INTO ТАРИФ (ID_ТАРИФА,НАИМЕНОВАНИЕ,РАЗМЕР_СКИДКИ,ЦЕНА_БЕЗ_СКИДКИ,ФИНАЛЬНАЯ_ЦЕНА) VALUES (?,?,?,?,?)" ,
                "DELETE FROM ТАРИФ  WHERE ID_ТАРИФА=?",
                "UPDATE ТАРИФ SET НАИМЕНОВАНИЕ=?, РАЗМЕР_СКИДКИ=?,ЦЕНА_БЕЗ_СКИДКИ=?,ЦЕНА_СО_СКИДКОЙ=? WHERE ID_ТАРИФА=?"),
        EXAM_RESULTS("SELECT * FROM УЧЕНИКИ_ЭКЗ","","",""),
        STUDENTS("SELECT * FROM УЧЕНИКИ","INSERT INTO УЧЕНИКИ (ID_УЧЕНИКА,ФИО,ПАСПОРТ,ПРОЖИВАНИЕ,ДАТА_РОЖДЕНИЯ,ID_ГРУППЫ) VALUES (?,?,?,?,?,?)",
                "DELETE FROM УЧЕНИКИ WHERE ID_УЧЕНИКА=?"
                ,"UPDATE УЧЕНИКИ SET ФИО=?,ПАСПОРТ=?,ПРОЖИВАНИЕ=?,ДАТА_РОЖДЕНИЯ=?,ID_ГРУППЫ=? WHERE ID_УЧЕНИКА=?"),
        GROUP("SELECT * FROM ГРУППА","INSERT INTO ГРУППА (ID_ГРУППЫ,НАИМЕНОВАНИЕ,КОД_ПРОГРАММЫ,НАЧАЛА_ОБУЧ,ОКОНЧАНИЯ_ОБУЧ) VALUES (?,?,?,?,?)",
                "DELETE FROM ГРУППА WHERE ID_ГРУППЫ=?",
                "UPDATE ГРУППА SET НАИМЕНОВАНИЕ=?,КОД_ПРОГРАММЫ=?,НАЧАЛА_ОБУЧ=?,ОКОНЧАНИЯ_ОБУЧ=? WHERE ID_ГРУППЫ=?"),
        TIMETABLE("SELECT * FROM РАСПИСАНИЕ","INSERT INTO РАСПИСАНИЕ (ID_РАСПИСАНИЯ,ID_ГРУППЫ,ID_ПРЕДМЕТА,НОМЕР_КАБИНЕТ,ID_ПЕРСОНАЛА,ДЕНЬ_НЕДЕЛИ) VALUES (?,?,?,?,?,?)",
                "DELETE FROM РАСПИСАНИЕ WHERE ID_РАПИСАНИЯ=?",
                "UPDATE РАСПИСАНИЕ SET ID_ГРУППЫ=?,ID_ПРЕДМЕТА=?,НОМЕР_КАБИНЕТА=?,ID_ПЕРСОНАЛА=?,ДЕНЬ_НЕДЕЛИ=? WHERE ID_РАСПИСАНИЯ=?");

        private String sqlSelectNow;
        private String sqlAddNow;
        private String sqlDeleteNow;
        private String sqlUpdateNow;

        Admin(String sqlSelectNow, String sqlAddNow, String sqlDeleteNow, String sqlUpdateNow) {
            this.sqlSelectNow = sqlSelectNow;
            this.sqlAddNow = sqlAddNow;
            this.sqlDeleteNow = sqlDeleteNow;
            this.sqlUpdateNow = sqlUpdateNow;
        }

        public String getSqlSelectNow() {
            return sqlSelectNow;
        }

        public String getSqlAddNow() {
            return sqlAddNow;
        }

        public String getSqlDeleteNow() {
            return sqlDeleteNow;
        }

        public String getSqlUpdateNow() {
            return sqlUpdateNow;
        }

    }

    public enum Instructor{


        EXAMS("SELECT * FROM ЭКЗАМЕНЫ", "INSERT INTO ПЕРСОНАЛ (ID_ЭКЗАМЕНА,НОМЕР_АВТО,ID_ПЕРСОНАЛА,ДАТА_ЭКЗАМЕНА,ID_ТАРИФА) VALUES (?,?,?,?,?)",
                "DELETE FROM ЭКЗАМЕНЫ WHERE ID_ЭКЗАМЕНА=?",
                "UPDATE ЭКЗАМЕНЫ SET НОМЕР_АВТО=?,ID_ПЕРСОНАЛА=?,ДАТА_ЭКЗАМЕНА=?,ID_ТАРИФА=? WHERE ID_ЭКЗАМЕНА=?"),
        CARS("SELECT * FROM АВТОМОБИЛИ","","",""),
        EXAM_RESULTS("SELECT * FROM УЧЕНИКИ_ЭКЗ","INSERT INTO УЧЕНИКИ_ЭКЗ (ID_УЧЕНИКА,ID_ЭКЗАМЕНА,ОЦЕНКА) VALUES (?,?,?)",
                "DELETE УЧЕНИКИ_ЭКЗ WHERE ID_УЧЕНИКА=? AND ID_ЭКЗАМЕНА=?",
                "UPDATE УЧЕНИКИ_ЭКЗ SET ОЦЕНКА = ? WHERE ID_УЧЕНИКА=? AND ID_ЭКЗАМЕНА=?");


        private String sqlSelectNow;
        private String sqlAddNow;
        private String sqlDeleteNow;
        private String sqlUpdateNow;


        Instructor(String sqlSelectNow, String sqlAddNow, String sqlDeleteNow, String sqlUpdateNow) {
            this.sqlSelectNow = sqlSelectNow;
            this.sqlAddNow = sqlAddNow;
            this.sqlDeleteNow = sqlDeleteNow;
            this.sqlUpdateNow = sqlUpdateNow;
        }

        public String getSqlSelectNow() {
            return sqlSelectNow;
        }

        public String getSqlAddNow() {
            return sqlAddNow;
        }

        public String getSqlDeleteNow() {
            return sqlDeleteNow;
        }

        public String getSqlUpdateNow() {
            return sqlUpdateNow;
        }


    }

    public enum Student {


        EXAMS("SELECT * FROM ЭКЗАМЕНЫ","","",""),
        EXAM_RESULTS("SELECT * FROM УЧЕНИКИ_ЭКЗ","","",""),
        GROUP("SELECT * FROM ГРУППА","","",""),
        TIMETABLE("SELECT * FROM РАСПИСАНИЕ","","",""),
        ITEMS("SELECT * FROM ПРЕДМЕТЫ","","","");

        private String sqlSelectNow;
        private String sqlAddNow;
        private String sqlDeleteNow;
        private String sqlUpdateNow;


        Student(String sqlSelectNow, String sqlAddNow, String sqlDeleteNow, String sqlUpdateNow) {
            this.sqlSelectNow = sqlSelectNow;
            this.sqlAddNow = sqlAddNow;
            this.sqlDeleteNow = sqlDeleteNow;
            this.sqlUpdateNow = sqlUpdateNow;
        }

        public String getSqlSelectNow() {
            return sqlSelectNow;
        }

        public String getSqlAddNow() {
            return sqlAddNow;
        }

        public String getSqlDeleteNow() {
            return sqlDeleteNow;
        }

        public String getSqlUpdateNow() {
            return sqlUpdateNow;
        }
    }

    public enum Teacher {


        EXAMS("SELECT * FROM ЭКЗАМЕНЫ","INSERT INTO ПЕРСОНАЛ (ID_ЭКЗАМЕНА,НОМЕР_АВТО,ID_ПЕРСОНАЛА,ДАТА_ЭКЗАМЕНА,ID_ТАРИФА) VALUES (?,?,?,?,?)",
                "DELETE FROM ЭКЗАМЕНЫ WHERE ID_ЭКЗАМЕНА=?",
                "UPDATE ЭКЗАМЕНЫ SET НОМЕР_АВТО=?,ID_ПЕРСОНАЛА=?,ДАТА_ЭКЗАМЕНА=?,ID_ТАРИФА=? WHERE ID_ЭКЗАМЕНА=?"),
        EXAM_RESULTS("SELECT * FROM УЧЕНИКИ_ЭКЗ","INSERT INTO УЧЕНИКИ_ЭКЗ (ID_УЧЕНИКА,ID_ЭКЗАМЕНА,ОЦЕНКА) VALUES (?,?,?)",
                "DELETE УЧЕНИКИ_ЭКЗ WHERE ID_УЧЕНИКА=? AND ID_ЭКЗАМЕНА=?",
                "UPDATE УЧЕНИКИ_ЭКЗ SET ОЦЕНКА = ? WHERE ID_УЧЕНИКА=? AND ID_ЭКЗАМЕНА=?"),
        GROUP("SELECT * FROM ГРУППА","","",""),
        TIMETABLE("SELECT * FROM РАСПИСАНИЕ","INSERT INTO РАСПИСАНИЕ (ID_РАСПИСАНИЯ,ID_ГРУППЫ,ID_ПРЕДМЕТА,НОМЕР_КАБИНЕТ,ID_ПЕРСОНАЛА,ДЕНЬ_НЕДЕЛИ) VALUES (?,?,?,?,?,?)",
                "DELETE FROM РАСПИСАНИЕ WHERE ID_РАПИСАНИЯ=?",
                "UPDATE РАСПИСАНИЕ SET ID_ГРУППЫ=?,ID_ПРЕДМЕТА=?,НОМЕР_КАБИНЕТА=?,ID_ПЕРСОНАЛА=?,ДЕНЬ_НЕДЕЛИ=? WHERE ID_РАСПИСАНИЯ=?"),
        ITEMS("SELECT * FROM ПРЕДМЕТЫ", "", "", "");

        private String sqlSelectNow;
        private String sqlAddNow;
        private String sqlDeleteNow;
        private String sqlUpdateNow;

        Teacher(String sqlSelectNow, String sqlAddNow, String sqlDeleteNow, String sqlUpdateNow) {
            this.sqlSelectNow = sqlSelectNow;
            this.sqlAddNow = sqlAddNow;
            this.sqlDeleteNow = sqlDeleteNow;
            this.sqlUpdateNow = sqlUpdateNow;
        }

        public String getSqlSelectNow() {
            return sqlSelectNow;
        }

        public String getSqlAddNow() {
            return sqlAddNow;
        }

        public String getSqlDeleteNow() {
            return sqlDeleteNow;
        }

        public String getSqlUpdateNow() {
            return sqlUpdateNow;
        }
    }


}
