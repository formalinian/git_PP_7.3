public class Program {
    public static String Text = "Эта книга адресована всем, кто изучает русский язык. Но состоит она не из правил, упражнений и учебных текстов. Для этого созданы другие замечательные учебники." +
            "" +
            "У этой книги совсем иная задача. Она поможет вам научиться не только разговаривать, но и размышлять по-русски. Книга, которую вы держите в руках, составлена из афоризмов и размышлений великих мыслителей, писателей, поэтов, философов и общественных деятелей различных эпох. Их мысли - о тех вопросах, которые не перестают волновать человечество." +
            "" +
            "Вы можете соглашаться или не соглашаться с тем, что прочитаете в этой книге. Возможно, вам покажется, что какие-то мысли уже устарели. Но вы должны обязательно подумать и обосновать, почему вы так считаете." +
            "" +
            "А еще вы узнаете и почувствуете, как прекрасно звучат слова любви, сострадания, мудрости и доброты на русском языке. dooge.";


    public static void main(String[] args) {
        //разбиваю текст на предложения
        String[] sentenceArray = Text.split("[.!?…]+ |[.]");
        for (int i = 0; i < sentenceArray.length; i++) {
            sentenceArray[i] = sentenceArray[i].toLowerCase();
        }

        //вывожу предложения
        for (String sentence : sentenceArray) {
            System.out.println(sentence);
        }

        int[][] matrixSentence = new int[sentenceArray.length][sentenceArray.length];
        for (int i = 0; i < matrixSentence.length; i++) {
            for (int j = 0; j < matrixSentence.length; j++) {
                matrixSentence[i][j] = 0;
            }
        }

        for (int i = 0; i < sentenceArray.length; i++) {
            String[] wordArrayI = sentenceArray[i].split("[ ,.!?…–-]+");
            for (int j = 0; j < sentenceArray.length; j++) {
                String[] wordArrayJ = sentenceArray[j].split("[ ,.!?…–-]+");
                for (int n = 0; n < wordArrayI.length; n++) {
                    for (int m = 0; m < wordArrayJ.length; m++) {
                        if (wordArrayI[n].contains(wordArrayJ[m]) && wordArrayI[n].length() == wordArrayJ[m].length()) {
                            matrixSentence[i][j] = 1;
                        }
                    }
                }
            }
        }

        int[] weightSentence = new int[sentenceArray.length];
        int weight = 0;
        for (int i = 0; i < matrixSentence.length; i++) {
            for (int j = 0; j < matrixSentence.length; j++) {
                weight = weight + matrixSentence[i][j];
            }
            weightSentence[i] = weight;
            weight = 0;
        }

        int maxWeight = 0;
        int indexMaxWeight = 0;
        for (int i = 0; i < weightSentence.length; i++) {
            if (weightSentence[i] > maxWeight) {
                maxWeight = weightSentence[i];
                indexMaxWeight = i;
            }
        }

        int[] indexOutSentence = new int[sentenceArray.length];
        int pastWeight = 0;
        int currentWeight = 0;
        for (int i = 0; i < indexOutSentence.length; i++) {
            indexOutSentence[i] = matrixSentence[i][indexMaxWeight];
            pastWeight = pastWeight + indexOutSentence[i];
        }

        for (int i = 0; i < sentenceArray.length; i++) {
            if (indexOutSentence[i] == 1) {
                for (int j = 0; j < sentenceArray.length; j++) {
                    if (indexOutSentence[j] == 1 && matrixSentence[i][j] == 1) {
                        matrixSentence[i][j] = 1;
                        currentWeight = currentWeight + matrixSentence[i][j];
                    } else {
                        matrixSentence[i][j] = 0;
                    }
                }

                if ((pastWeight - currentWeight) < currentWeight) {
                    for (int j = 0; j < sentenceArray.length; j++) {
                        indexOutSentence[j] = matrixSentence[i][j];
                    }
                    pastWeight = currentWeight;
                } else {
                    indexOutSentence[i] = 0;
                }
                currentWeight = 0;
            }
        }
        System.out.println();
        for (int i = 0; i < sentenceArray.length; i++) {
            if (indexOutSentence[i] == 1) {
                System.out.println((i + 1) + ") " + sentenceArray[i]);
            }
        }

    }
}
