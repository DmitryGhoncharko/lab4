import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends JPanel implements ActionListener{
    private final JFrame jFrame;
    TextArea  textArea;
    private boolean buttonClicked = false;

    public Task(String title) throws HeadlessException {
        //заглавие приложения
        jFrame = new JFrame(title);
        //закрыть приложение если нажали на крестик
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //размеры окна
        jFrame.setSize(1000, 1000);
        //запретили менять размер
        jFrame.setResizable(false);
        //сделали видимым фрейм приложения
        jFrame.setVisible(true);
        //layout это разметка задали что наше приложения будет размечаться при помощи бордер лейаут
        this.setLayout(new BorderLayout());
        TextField textField = new TextField("Введите имя каталога");
        //устанавливаем обработчик на текст филд который сработает когда нажимаем ентер когда ввели текст в текст филд
        textField.addActionListener(this);
        //второй параметр говорит что текст филд должен быть в самом верху приложения
        this.add(textField,BorderLayout.PAGE_START);
        textArea = new TextArea();
        //второй параметр гооврит что текст ареа должен быть в центре приложения
        this.add(textArea,BorderLayout.CENTER);
        jFrame.add(this);
    }
    //получаем список файлов по указанному пути например D:// вернет список всех файлов в диретории диск д
    private List<File> getAllFilesFromPath(String path){
        File dir = new File(path); //path указывает на директорию
        List<File> files = new ArrayList<>();
        for ( File file : dir.listFiles() ){
            if ( file.isFile() )
                files.add(file);
        }
        return files;
    }
    public static void main(String[] args) {
        Task task = new Task("App");
    }
    //метод срабатывает когда нажимаем enter на текст филд
    @Override
    public void actionPerformed(ActionEvent e) {
        String path = e.getActionCommand();
        List<File> files = getAllFilesFromPath(path);
        StringBuilder stringBuilder = new StringBuilder("");
        for(File file : files){
            //разделяем наше название по точке data.txt получим массив из двух значений data и txt
            String fileName = file.getName().split("[.]")[0];
            String fileType = file.getName().split("[.]")[1];
            //получаем размер файла
            long fileSize = file.length();
            Date lastMod  = new Date(file.lastModified());
            stringBuilder.append("Имя файла ").append(fileName).append(" тип файла ").append(fileType).append(" размер файла в байтах ").append(fileSize).append(" дата последнего изменения ").append(lastMod).append("\n");
        }
        
        textArea.setText(stringBuilder.toString());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

}
