
public class StringUtils {

	public static String findSortedSequence(String str) {
		// TODO
		String[] arrOfStr = str.split(" ");
		int startIndex=0,endInndex=0, oldCounter = 0;

			for (int i = 0; i < arrOfStr.length; i++)
			{
				int counter = 1;
				int tempstartIndex=i;
                //private method to compare next string strings see below
				while (i+1 < arrOfStr.length && stringCompare(arrOfStr[i+1], arrOfStr[i])>=0)
				{
					i++;
					counter++;
				}
				//we find new longest Sequence
				if (oldCounter <= counter)
				{oldCounter = counter;
				    endInndex=i;
				  startIndex=tempstartIndex;
				}
		}//print the Sequence from the string array from given index's
		return arrToStr(arrOfStr,startIndex,endInndex);

	}


	public static boolean isEditDistanceOne(String a, String b) {
		// TODO
		int countEditds = 0, i = 0, j = 0;
		int aSize = a.length(), bSize = b.length();
		int diff_size = bSize - aSize;
        //diff_size and bIsBigger help us to manage witch string is bigger
		if (Math.abs(diff_size) > 1)
			return false;
		boolean bIsBigger = diff_size >= 0;
		//we just chose j to be the bigger index
		while (bIsBigger ? j < aSize : j < bSize)
		{
			if (!(bIsBigger ? a.charAt(i) == b.charAt(j) : a.charAt(j) == b.charAt(i)))
			{
				countEditds++;
				if (countEditds > 1)
					return false;
				//we will remove one letter from the longest string, its equal to only j++
				if (diff_size != 0)
					i--;
			}
			i++;
			j++;
		}
		return true;
	}


	//*       private methods         *//


	//use each char in range A-Z as int to compare,return positve number if str bigger else zero if equal negative if smaller
	private static int stringCompare(String str1, String str2)
	{
		for (int i = 0; i < str1.length() && i < str2.length(); i++) {
			if (!((int)str1.charAt(i) == (int)str2.charAt(i)))
				 return (int)str1.charAt(i) - (int)str2.charAt(i);
			 }
	return str1.length()-str2.length();
	}

    //simple loop to print index in given range
	private static String arrToStr(String[] arr,int i,int j)
	{
		String st="";
		if(arr.length<1)
			return  "";
		while (i<=j){
			st+=arr[i]+" ";
			i++;
		}
		return st.substring(0,st.length()-1);
	}
}
