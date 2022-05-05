/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weka;

import weka.associations.Apriori;
import weka.core.Instances;

/**
 *
 * @author Viet Khoa
 */
public class MyApriorilModel extends MyKnowledgeModel {
    Apriori apriori;
    Instances newData;

    public MyApriorilModel() {
    }

    public MyApriorilModel(String filename, String m_opts, String d_opts) throws Exception {
        super(filename, m_opts, d_opts);
        this.apriori=new Apriori();
    }
    public void mineAssociationRules() throws Exception{
        //Lọc dữ liệu
        this.newData=removeData(this.dataset);
        //this.newData=convertData(this.dataset);
        //Thiết lập thông số cho model Apriori
        apriori.setOptions(this.model_options);
        //Khai phá luật kết hợp bằng thuật toán Appriori
        apriori.buildAssociations(this.newData);
    }

    @Override
    public String toString() {
        return apriori.toString();
    }
    
}
