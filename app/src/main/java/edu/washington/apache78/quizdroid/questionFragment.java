package edu.washington.apache78.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class questionFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "current";
    private static final String ARG_PARAM2 = "topic";
    private static final int CURRENT = 0;
    private static final String TOPIC = "topic";


    // TODO: Rename and change types of parameters
    private int current;
    private String topic;
    private String gave;
    private String answer;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment question.
     */
    // TODO: Rename and change types and number of parameters
    public static questionFragment newInstance(String param1, String param2) {
        questionFragment fragment = new questionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public questionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.current = getArguments().getInt("CURRENT");
            topic = getArguments().getString("TOPIC");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_question, container, false);
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.choices);
        radioGroup.setOnCheckedChangeListener(this);
        Button submit = (Button) view.findViewById(R.id.btnSubmit);
        submit.setOnClickListener(this);
        setUp(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        mListener.answered(gave, answer);



    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton selected = (RadioButton) group.findViewById(checkedId);
        gave = selected.getText().toString();
        makeVisibile();
    }

    private void makeVisibile() {
        Button submit = (Button) getActivity().findViewById(R.id.btnSubmit);
        submit.setVisibility(View.VISIBLE);
    }

    public void setUp(View view){
        TextView question = (TextView) view.findViewById(R.id.question);
        QuizApp app = QuizApp.getInstance();

        ITopicRepository repo = app.getRepo();
        Question q = repo.getQuestion(current);
        question.setText(q.getQuestionText());
        String[] choices = q.getAnswerArray();
        RadioButton one = (RadioButton) view.findViewById(R.id.one);
        RadioButton two = (RadioButton) view.findViewById(R.id.two);
        RadioButton three = (RadioButton) view.findViewById(R.id.three);
        RadioButton four = (RadioButton) view.findViewById(R.id.four);
        one.setText(choices[0]);
        two.setText(choices[1]);
        three.setText(choices[2]);
        four.setText(choices[3]);

        answer = choices[q.getAnswerIndex()-1];
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void answered(String given, String Answer);
        // TODO: Update argument type and name
        public void onFragmentInteraction(String S);
    }

}
