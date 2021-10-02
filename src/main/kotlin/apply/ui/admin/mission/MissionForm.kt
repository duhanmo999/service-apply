package apply.ui.admin.mission

import apply.application.EvaluationSelectData
import apply.application.MissionData
import com.vaadin.flow.component.datetimepicker.DateTimePicker
import com.vaadin.flow.component.radiobutton.RadioButtonGroup
import com.vaadin.flow.component.select.Select
import com.vaadin.flow.component.textfield.TextArea
import com.vaadin.flow.component.textfield.TextField
import support.views.BindingIdentityFormLayout
import support.views.createBooleanRadioButtonGroup
import support.views.createItemSelect

class MissionForm() : BindingIdentityFormLayout<MissionData>(MissionData::class) {
    private val title: TextField = TextField("과제명")
    private val description: TextArea = TextArea("설명")
    private val evaluation: Select<EvaluationSelectData> = createItemSelect<EvaluationSelectData>("평가").apply {
        setItemLabelGenerator(EvaluationSelectData::title)
        isEmptySelectionAllowed = false
    }
    private val startDateTime: DateTimePicker = DateTimePicker("시작 일시")
    private val endDateTime: DateTimePicker = DateTimePicker("종료 일시")
    private val submittable: RadioButtonGroup<Boolean> = createBooleanRadioButtonGroup("제출 가능 여부", "가능", "불가능", false)

    init {
        add(title, evaluation, startDateTime, endDateTime, description, submittable)
        setResponsiveSteps(ResponsiveStep("0", 1))
        drawRequired()
    }

    constructor(
        evaluations: List<EvaluationSelectData>
    ) : this() {
        evaluation.setItems(evaluations)
    }

    override fun bindOrNull(): MissionData? {
        return bindDefaultOrNull()
    }

    override fun fill(data: MissionData) {
        fillDefault(data)
    }
}