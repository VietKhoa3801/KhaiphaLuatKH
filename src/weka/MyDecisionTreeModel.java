/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weka;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.Debug.Random;

/**
 *
 * @author Viet Khoa
 */
public class MyDecisionTreeModel extends MyKnowledgeModel {
    J48 tree;
    public MyDecisionTreeModel(String filename, String m_opts, String d_opts) throws Exception {
        super(filename, m_opts, d_opts);
    }
    public void buildDecisionTree() throws Exception{
        //Tạo tập dữ liệu train và test
        this.trainset=divideTrainTest(this.dataset,80,false);
        this.testset=divideTrainTest(this.dataset,80,true);
        this.trainset.setClassIndex(this.trainset.numAttributes()-1);
        this.testset.setClassIndex(this.testset.numAttributes()-1);
        //Thiết lập thông số cho mô hình cây quyết định
        tree=new J48();
        tree.setOptions(this.model_options);
        //Huấn luyện mô hình vs tập dữ liệu train
        tree.buildClassifier(this.trainset);
    }
    public void evaluateDecisionTree() throws Exception{
        Random rnd=new Debug.Random(1);
        int folds=10;
        Evaluation eval=new Evaluation(this.trainset);
        eval.crossValidateModel(tree,this.testset, folds, rnd);
        System.out.println(eval.toSummaryString("\n Ket qua danh gia mo hinh 10-folds cross-validation \n------\n",false));
    }

    @Override
    public String toString() {
        return tree.toSummaryString(); 
    }
    
}
