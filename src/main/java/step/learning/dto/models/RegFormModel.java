package step.learning.dto.models;

import org.apache.commons.fileupload.FileItem;
import step.learning.services.formparse.FormParseResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class RegFormModel {
    // region fields
    private String name;
    private String login;
    private String password;
    private String repeat;
    private String email;
    private Date birthdate;
    private boolean isAgree;
    private String avatar; //filename for avatar
    // endregion

//    public RegFormModel(HttpServletRequest request) throws ParseException {
//        this.setName(request.getParameter("reg-name"));
//        this.setLogin(request.getParameter("reg-login"));
//        this.setPassword(request.getParameter("reg-password"));
//        this.setRepeat(request.getParameter("reg-repeat"));
//        this.setEmail(request.getParameter("reg-email"));
//        this.setBirthdate(request.getParameter("reg-birthdate"));
//        this.setAgree(request.getParameter("reg-rules"));
//    }

    public RegFormModel(FormParseResult result) throws ParseException {
        Map<String,String> fields = result.getFields();
        this.setName(fields.get("reg-name"));
        this.setLogin(fields.get("reg-login"));
        this.setPassword(fields.get("reg-password"));
        this.setRepeat(fields.get("reg-repeat"));
        this.setEmail(fields.get("reg-email"));
        this.setBirthdate(fields.get("reg-birthdate"));
        this.setAgree(fields.get("reg-rules"));
        this.setAvatar(result);

    }
    public Map<String, String> getErrorMessages(){
        Map<String, String> result = new HashMap<>();
        if (login == null || "".equals(login)){
            result.put("login","Логін не може бути порожнім");
        } else if (!Pattern.matches("^[a-zA-Z0-9]+$",login)) {
            result.put("login","Логін не відповідає шаблону: тільки літери та цифри");
        }

        if (name == null || "".equals(name)){
            result.put("name","Ім'я не може бути порожнім");
        }

        if (email == null || "".equals(email)){
            result.put("email","Email не може бути порожнім");
        }

        if (getBirthdateAsString() == null || "".equals(getBirthdateAsString())){
            result.put("birthdate","Дата народження не може бути порожньою");
        }
        return result;
    }

    // region accessors

    public String getAvatar() {
        return avatar;
    }

    private void setAvatar(FormParseResult result) throws ParseException {
        Map<String, FileItem> files = result.getFiles();
        if( ! files.containsKey("reg-avatar")) {
            this.avatar = null;
            return;
        }
        // є переданий файл, обробляємо його
        FileItem item = (files.get("reg-avatar"));
        // директорія завантаження файлів (./ - це директорія сервера (Tomcat))
        String targetDir = result.getRequest()
                .getServletContext() // контекст - "оточення" сервлету, з якого дізнаємось шляхи
                .getRealPath("./upload/avatar/");
        String submitterdFilename = item.getName();
        // Визначити тип файлу (розширення) та перевірити на перелік дозволених
        String ext = submitterdFilename.substring(submitterdFilename.lastIndexOf('.'));
        if (ext.equals(".png") || ext.equals(".jpg"))
        {

        }
        else
        {
            this.avatar = null;
            return;
        }
        String savedFilename;
        File savedFile;
        do{
            savedFilename = UUID.randomUUID().toString().substring(0,8) + ext;
            savedFile = new File(targetDir + savedFilename);
        } while (savedFile.exists());
        try {
            item.write(savedFile);
        } catch (Exception e) {
            throw new ParseException("File upload error",0);
        }
        this.avatar = savedFilename;
    }
    public String getBirthdateAsString(){
        if(birthdate == null)
        {
            return "";
        }
        return formDateFormat.format(getBirthdate());
    }
    public void setBirthdate(String birthdate) throws ParseException {
        if(birthdate == null || "".equals(birthdate))
        {
            this.birthdate = null;
        }
        else
        {
            this.birthdate = formDateFormat.parse(birthdate);
        }
    }
    public void setAgree(String isAgree)
    {
        this.isAgree = "on".equalsIgnoreCase(isAgree) || "true".equalsIgnoreCase(isAgree);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }
    // endregion

    private static final SimpleDateFormat formDateFormat =
            new SimpleDateFormat("yyyy-MM-dd");

}
